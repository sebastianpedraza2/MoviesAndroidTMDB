package com.pedraza.sebastian.movie_data.datasource.remote

import com.pedraza.sebastian.movie_data.api.MoviesService
import com.pedraza.sebastian.movie_data.entities.dto.MovieDetailDto
import com.pedraza.sebastian.movie_data.entities.dto.MovieListDto
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
    private val moviesService: MoviesService,
    private val apiKey: String
) : MoviesRemoteDataSource {
    override suspend fun getMovieList(page: Int): Response<MovieListDto> =
        moviesService.getMovies(apiKey = apiKey, page = page)

    override suspend fun getMovieDetail(id: Int): Response<MovieDetailDto> =
        moviesService.getMovieDetail(apiKey = apiKey, id = id)

    override suspend fun getTopRatedMovies(page: Int): Response<MovieListDto> =
        moviesService.getTopRatedMovies(apiKey = apiKey, page = page)

    override suspend fun getUpcomingMovies(page: Int): Response<MovieListDto> =
        moviesService.getUpcomingMovies(apiKey = apiKey, page = page)
}