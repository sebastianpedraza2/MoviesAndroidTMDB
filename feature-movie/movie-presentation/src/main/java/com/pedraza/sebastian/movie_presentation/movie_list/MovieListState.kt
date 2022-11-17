package com.pedraza.sebastian.movie_presentation.movie_list

import com.pedraza.sebastian.movie_domain.models.Movie


data class MovieListState(
    val movieList: List<Movie> = emptyList(),
    val favoritesMovieList: List<Movie> = emptyList(),
    val movieListUiState: MovieListUiState = MovieListUiState.Init,
    val filterBy: FilterMoviesBy = FilterMoviesBy.NoFilter,
    val nextPage: Int = 1,
    val currentEvent: MovieListEvent = MovieListEvent.GetMovieList
)

sealed interface MovieListUiState {
    object Init : MovieListUiState
    object Loading : MovieListUiState
    object MovieListFetched : MovieListUiState
}

sealed interface FilterMoviesBy {
    object NoFilter : FilterMoviesBy
    object TopRated : FilterMoviesBy
    object Upcoming : FilterMoviesBy
}
