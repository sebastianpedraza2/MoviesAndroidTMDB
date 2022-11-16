package com.pedraza.sebastian.movie_domain.usecases.favorites

import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetAllMoviesFromFavoritesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetAllMoviesFromFavoritesUseCase {
    override suspend fun invoke(): Result<Flow<List<Movie>>> =
        moviesRepository.getAllMoviesFromFavorites()

}