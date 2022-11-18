package com.pedraza.sebastian.movie_presentation.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pedraza.sebastian.core.dimensions.LocalSpacing
import com.pedraza.sebastian.movie_domain.models.MovieDetail
import com.pedraza.sebastian.core.R

@Composable
fun MovieDetailContentView(movieDetail: MovieDetail) {
    val spacing = LocalSpacing.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                spacing.spaceMedium
            ), shape = RoundedCornerShape(spacing.spaceSmall),
        elevation = spacing.spaceExtraSmall
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            if (!movieDetail.thumbnail.isNullOrEmpty()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movieDetail.getCompleteThumbnailUrl())
                        .crossfade(true)
                        .build(),
                    placeholder = null,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(spacing.spacing200dp)
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = spacing.spaceLarge,
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium
                    ),
                text = movieDetail.name,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 20.sp
                ),
                fontWeight = FontWeight.ExtraBold,
                maxLines = 2,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = spacing.spaceLarge,
                        start = spacing.spaceMedium,
                        end = spacing.spaceMedium
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movieDetail.date,
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.LightGray
                    ),
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = if (movieDetail.time != null) stringResource(
                        id = R.string.time_minutes,
                        movieDetail.time.toString()
                    ) else ""
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                text = movieDetail.overview,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 14.sp
                ),
                fontWeight = FontWeight.Normal,
            )
        }
    }
}
