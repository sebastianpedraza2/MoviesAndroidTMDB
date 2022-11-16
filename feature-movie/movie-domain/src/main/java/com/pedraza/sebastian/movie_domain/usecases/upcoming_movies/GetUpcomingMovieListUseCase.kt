package com.pedraza.sebastian.movie_domain.usecases.upcoming_movies

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.MovieList

interface GetUpcomingMovieListUseCase {
    suspend fun invoke(page: Int): Result<MovieList>
}