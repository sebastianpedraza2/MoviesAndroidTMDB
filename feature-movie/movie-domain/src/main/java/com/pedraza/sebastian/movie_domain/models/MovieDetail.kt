package com.pedraza.sebastian.movie_domain.models

data class MovieDetail(
    val id: Int,
    val name: String,
    val date: String,
    val overview: String,
    val time: Int,
    val score: Double,
    val thumbnail: String?
)
