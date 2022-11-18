package com.pedraza.sebastian.movie_presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedraza.sebastian.android_helpers.compose.MovieLottieAnimation
import com.pedraza.sebastian.core.dimensions.LocalSpacing
import com.pedraza.sebastian.core.utils.UiEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieListScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacing.spaceSmall),
        contentAlignment = Alignment.Center
    ) {
        when (uiState.value.movieListUiState) {
            is MovieListUiState.Loading -> MovieLottieAnimation()
            is MovieListUiState.MovieListFetched -> MovieListContent(
                movieList = uiState.value.movieList,
                filterMoviesBy = uiState.value.filterBy,
                viewModel = viewModel
            )
            is MovieListUiState.Init -> {
                viewModel.getFavoriteMovies()
                viewModel.getMoviesList()
            }
        }
    }
}
