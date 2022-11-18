package com.pedraza.sebastian.movie_presentation.movie_detail

import com.pedraza.sebastian.movie_domain.models.MovieDetail

data class MovieDetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val movieDetailUiState: MovieDetailUiState = MovieDetailUiState.Init
)

sealed interface MovieDetailUiState {
    object Init : MovieDetailUiState
    object Loading : MovieDetailUiState
    object MovieDetailFetched : MovieDetailUiState

}
