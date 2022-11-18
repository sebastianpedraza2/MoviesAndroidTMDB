package com.pedraza.sebastian.movie_presentation.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedraza.sebastian.android_helpers.compose.MovieLottieAnimation
import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.dimensions.LocalSpacing
import com.pedraza.sebastian.core.utils.UiEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel(),
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
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(Modifier.fillMaxSize().padding(spacing.spaceSmall)) {
            Image(
                modifier = Modifier
                    .size(spacing.spaceLarge)
                    .align(Start)
                    .padding(top = spacing.spaceMedium)
                    .clickable {
                        viewModel.onNavigateBack()
                    },
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
                contentDescription = null,
            )
            when (uiState.value.movieDetailUiState) {
                MovieDetailUiState.Init -> {
                    viewModel.getMovieDetail(movieId)
                }
                MovieDetailUiState.Loading -> MovieLottieAnimation()
                MovieDetailUiState.MovieDetailFetched -> MovieDetailContentView(uiState.value.movieDetail)
            }
        }
    }
}
