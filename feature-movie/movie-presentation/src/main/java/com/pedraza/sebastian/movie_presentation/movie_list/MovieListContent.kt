package com.pedraza.sebastian.movie_presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.dimensions.LocalSpacing
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_presentation.components.ItemLoadingBox
import com.pedraza.sebastian.movie_presentation.components.MovieItemCard
import com.pedraza.sebastian.movie_presentation.components.SelectableButton


@Composable
fun MovieListContent(
    movieList: List<Movie>,
    filterMoviesBy: FilterMoviesBy,
    viewModel: MovieListViewModel
) {
    val listState = rememberLazyListState()
    val spacing = LocalSpacing.current
    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
        item {
            Text(
                text = stringResource(id = R.string.filter_by),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(
                    top = spacing.spaceLarge,
                    start = spacing.spaceMedium,
                    end = spacing.spaceMedium
                )
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                horizontalArrangement = Arrangement.spacedBy(
                    spacing.spaceSmall,
                    alignment = Alignment.Start
                )
            ) {
                SelectableButton(
                    text = stringResource(id = R.string.no_filter),
                    isSelected = filterMoviesBy is FilterMoviesBy.NoFilter,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal, fontSize = 14.sp
                    )
                ) {
                    viewModel.onMovieListEvent(MovieListEvent.GetMovieList)
                }
                SelectableButton(
                    text = stringResource(id = R.string.top_rated),
                    isSelected = filterMoviesBy is FilterMoviesBy.TopRated,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal, fontSize = 14.sp
                    )
                ) {
                    viewModel.onMovieListEvent(MovieListEvent.FilterByTopRatedPressed)
                }
                SelectableButton(
                    text = stringResource(id = R.string.upcoming),
                    isSelected = filterMoviesBy is FilterMoviesBy.Upcoming,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal, fontSize = 14.sp
                    )
                ) {
                    viewModel.onMovieListEvent(MovieListEvent.FilterByUpcomingPressed)
                }
            }
        }
        items(movieList) { movie ->
            if (movie.id > 0) {
                MovieItemCard(
                    movie = movie,
                    isFavorite = movie.isFavorite,
                    onNavigateToMovieDetail = { id ->
                        viewModel.onNavigateToMovieDetail(id)
                    }
                ) {
                    viewModel.onMovieListEvent(
                        MovieListEvent.OnFavoritePressed(movie)
                    )
                }
            } else if (movie.id == 0) {
                ItemLoadingBox()
            }
        }
    }
    listState.OnBottomReached(buffer = 1) {
        viewModel.getMoviesList()
    }
}

@Composable
fun LazyListState.OnBottomReached(
    buffer: Int = 0,
    loadMore: () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) loadMore()
            }
    }
}
