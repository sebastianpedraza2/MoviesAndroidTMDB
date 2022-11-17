package com.pedraza.sebastian.movie_presentation.movie_list

import com.pedraza.sebastian.movie_domain.models.Movie

sealed interface MovieListEvent {
    data class OnFavoritePressed(val movie: Movie) : MovieListEvent
    object GetMovieList : MovieListEvent
    object FilterByTopRatedPressed : MovieListEvent
    object FilterByUpcomingPressed : MovieListEvent
}
