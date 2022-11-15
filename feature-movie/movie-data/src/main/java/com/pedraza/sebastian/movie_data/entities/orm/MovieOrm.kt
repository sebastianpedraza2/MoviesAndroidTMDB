package com.pedraza.sebastian.movie_data.entities.orm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieOrm(
    @PrimaryKey
    val id: Int,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)