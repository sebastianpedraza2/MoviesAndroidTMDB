package com.pedraza.sebastian.movie_data.datasource.remote

import com.pedraza.sebastian.movie_data.entities.dto.MovieDetailDto
import com.pedraza.sebastian.movie_data.entities.dto.MovieListDto
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getMovieList(page: Int): Response<MovieListDto>
    suspend fun getMovieDetail(id: Int): Response<MovieDetailDto>
    suspend fun getTopRatedMovies(page: Int): Response<MovieListDto>
    suspend fun getUpcomingMovies(page: Int): Response<MovieListDto>
}