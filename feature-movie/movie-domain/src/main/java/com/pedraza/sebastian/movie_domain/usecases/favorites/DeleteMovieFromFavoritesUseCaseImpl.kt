package com.pedraza.sebastian.movie_domain.usecases.favorites

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository

class DeleteMovieFromFavoritesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    DeleteMovieFromFavoritesUseCase {
    override suspend fun invoke(movie: Movie): Result<Unit> =
        moviesRepository.deleteMovieFromFavorites(movie)
}