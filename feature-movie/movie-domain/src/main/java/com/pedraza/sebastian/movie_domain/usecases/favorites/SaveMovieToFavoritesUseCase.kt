package com.pedraza.sebastian.movie_domain.usecases.favorites

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie

interface SaveMovieToFavoritesUseCase {
    suspend fun invoke(movie: Movie): Result<Unit>
}