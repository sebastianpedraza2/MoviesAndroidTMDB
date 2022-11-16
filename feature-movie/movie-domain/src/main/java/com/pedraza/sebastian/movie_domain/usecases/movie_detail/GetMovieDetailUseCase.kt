package com.pedraza.sebastian.movie_domain.usecases.movie_detail

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieDetail

interface GetMovieDetailUseCase {
    suspend fun invoke(id: Int): Result<MovieDetail>
}