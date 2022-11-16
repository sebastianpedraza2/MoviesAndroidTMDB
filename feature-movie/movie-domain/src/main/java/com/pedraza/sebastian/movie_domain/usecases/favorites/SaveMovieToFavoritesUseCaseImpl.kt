package com.pedraza.sebastian.movie_domain.usecases.favorites

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class SaveMovieToFavoritesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    SaveMovieToFavoritesUseCase {
    override suspend fun invoke(movie: Movie): Result<Unit> =
        moviesRepository.saveMovieToFavorites(movie)
}