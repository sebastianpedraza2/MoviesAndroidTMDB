package com.pedraza.sebastian.movie_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.pedraza.sebastian.core.dimensions.LocalSpacing
import com.pedraza.sebastian.core.navigation.Routes
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.movie_presentation.movie_list.MovieListEvent


@Composable
fun MovieItemCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    isFavorite: Boolean,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onFavoritePressed: (MovieListEvent) -> Unit,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Card(
        modifier = modifier
            .padding(
                spacing.spaceExtraSmall
            )
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onNavigate(UiEvent.Navigate(Routes.MOVIE_DETAIL)) },
        shape = RoundedCornerShape(spacing.spaceSmall),
        elevation = spacing.spaceExtraSmall
    )
    {
        Surface {
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(spacing.spaceSmall),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context)
                            .data(data = movie.getCompleteThumbnailUrl())
                            .apply(block = fun ImageRequest.Builder.() {
                                scale(Scale.FIT)
                                placeholder(R.drawable.movie_placeholder)
                            }).build()
                    ),
                    contentDescription = movie.overview,
                    modifier = Modifier
                        .weight(55f, fill = false)
                        .clip(RoundedCornerShape(spacing.spaceExtraSmall))
                )

                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(55f)
                        .padding(spacing.spaceSmall)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.ExtraBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = stringResource(id = R.string.rating, movie.score),
                        style = MaterialTheme.typography.h6.copy(
                            color = Color.LightGray
                        ),
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.overline,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Image(
                    modifier = Modifier
                        .padding(spacing.spaceSmall)
                        .fillMaxHeight()
                        .weight(13f)
                        .clickable {
                            onFavoritePressed(MovieListEvent.OnFavoritePressed(movie))
                        },
                    painter = if (isFavorite) painterResource(id = R.drawable.ic_baseline_favorite_24) else painterResource(
                        id = R.drawable.ic_baseline_favorite_border_24
                    ),
                    contentDescription = null,
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieItemPreview() {
    MovieItemCard(
        movie = Movie(name = "Avengers", date = "02/03/2022", isFavorite = true),
        isFavorite = true, onNavigate = {}) {

    }
}