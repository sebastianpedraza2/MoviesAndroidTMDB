package com.pedraza.sebastian.movie_domain.models

data class MovieList(
    val page: Int = 1,
    val movies: List<Movie> = listOf(),
    val totalPages: Int = 1,
    val totalResults: Int = 0
)
