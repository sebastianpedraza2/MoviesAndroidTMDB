package com.pedraza.sebastian.movie_data.entities.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double?
)
