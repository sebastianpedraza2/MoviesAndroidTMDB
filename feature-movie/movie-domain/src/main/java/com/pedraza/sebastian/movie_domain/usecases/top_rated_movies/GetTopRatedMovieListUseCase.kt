package com.pedraza.sebastian.movie_domain.usecases.top_rated_movies

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList

interface GetTopRatedMovieListUseCase {
    suspend fun invoke(page: Int): Result<MovieList>
}