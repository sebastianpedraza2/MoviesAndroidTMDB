package com.pedraza.sebastian.movie_data.datasource.local

import com.pedraza.sebastian.movie_data.db.MovieDao
import com.pedraza.sebastian.movie_data.entities.orm.MovieOrm
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MoviesLocalDataSource {
    override suspend fun insertMovie(movie: MovieOrm) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: MovieOrm) {
        movieDao.deleteMovie(movie)
    }

    override fun getAllMovies(): Flow<List<MovieOrm>> =
        movieDao.getAllMovies()
}