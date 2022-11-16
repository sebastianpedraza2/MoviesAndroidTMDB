package com.pedraza.sebastian.movie_domain.usecases.top_rated_movies

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class GetTopRatedMovieListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetTopRatedMovieListUseCase {
    override suspend fun invoke(page: Int): Result<MovieList> =
        moviesRepository.getTopRatedMovies(page)
}