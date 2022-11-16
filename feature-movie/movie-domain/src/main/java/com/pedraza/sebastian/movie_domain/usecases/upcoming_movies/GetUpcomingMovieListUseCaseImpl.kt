package com.pedraza.sebastian.movie_domain.usecases.upcoming_movies

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class GetUpcomingMovieListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetUpcomingMovieListUseCase {
    override suspend fun invoke(page: Int): Result<MovieList> =
        moviesRepository.getUpcomingMovies(page)
}