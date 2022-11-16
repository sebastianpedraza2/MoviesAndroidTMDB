package com.pedraza.sebastian.movie_domain.repositories

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.models.MovieDetail
import com.pedraza.sebastian.movie_domain.models.MovieList
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovieList(page: Int): Result<MovieList>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail>
    suspend fun getTopRatedMovies(page: Int): Result<MovieList>
    suspend fun getUpcomingMovies(page: Int): Result<MovieList>
    suspend fun saveMovieToFavorites(movie: Movie): Result<Unit>
    suspend fun deleteMovieFromFavorites(movie: Movie): Result<Unit>
    fun getAllMoviesFromFavorites(): Result<Flow<List<Movie>>>
}