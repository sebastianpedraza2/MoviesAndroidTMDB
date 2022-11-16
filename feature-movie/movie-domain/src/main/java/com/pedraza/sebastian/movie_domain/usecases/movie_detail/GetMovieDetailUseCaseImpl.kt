package com.pedraza.sebastian.movie_domain.usecases.movie_detail

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieDetail
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class GetMovieDetailUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieDetailUseCase {
    override suspend fun invoke(id: Int): Result<MovieDetail> = moviesRepository.getMovieDetail(id)
}