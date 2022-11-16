package com.pedraza.sebastian.movie_data.datasource.local

import com.pedraza.sebastian.movie_data.entities.orm.MovieOrm
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun insertMovie(movie: MovieOrm)
    suspend fun deleteMovie(movie: MovieOrm)
    fun getAllMovies(): Flow<List<MovieOrm>>
}