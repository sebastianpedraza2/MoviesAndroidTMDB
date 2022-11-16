package com.pedraza.sebastian.movie_domain.usecases.movie_list

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class GetMovieListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieListUseCase {
    override suspend fun invoke(page: Int): Result<MovieList> =
        moviesRepository.getMovieList(page)
}