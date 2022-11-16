package com.pedraza.sebastian.movie_domain.usecases.movie_list

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList

interface GetMovieListUseCase {
    suspend fun invoke(page: Int): Result<MovieList>
}