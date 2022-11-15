package com.pedraza.sebastian.movie_domain.models

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val overview: String = "",
    val score: Double = 0.0,
    val thumbnail: String? = "",
    var isFavorite: Boolean = false
)
