package com.pedraza.sebastian.movie_domain.usecases.favorites

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface GetAllMoviesFromFavoritesUseCase {
    suspend fun invoke(): Result<Flow<List<Movie>>>
}