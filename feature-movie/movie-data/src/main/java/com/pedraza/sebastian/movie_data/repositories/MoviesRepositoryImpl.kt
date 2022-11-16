package com.pedraza.sebastian.movie_data.repositories

import com.pedraza.sebastian.core.utils.UiText
import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.movie_data.datasource.local.MoviesLocalDataSource
import com.pedraza.sebastian.movie_data.datasource.remote.MoviesRemoteDataSource
import com.pedraza.sebastian.movie_data.mappers.MovieDetailDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieListDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieOrmMapper
import com.pedraza.sebastian.movie_data.utils.toResult
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.models.MovieDetail
import com.pedraza.sebastian.movie_domain.models.MovieList
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val movieListDtoMapper: MovieListDtoMapper,
    private val movieDetailDtoMapper: MovieDetailDtoMapper,
    private val movieOrmMapper: MovieOrmMapper
) : MoviesRepository {
    override suspend fun getMovieList(page: Int): Result<MovieList> {
        return try {
            val response = moviesRemoteDataSource.getMovieList(page)
            response.toResult(entityMapper = movieListDtoMapper)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetail> {
        return try {
            val response = moviesRemoteDataSource.getMovieDetail(id)
            response.toResult(entityMapper = movieDetailDtoMapper)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override suspend fun getTopRatedMovies(page: Int): Result<MovieList> {
        return try {
            val response = moviesRemoteDataSource.getTopRatedMovies(page)
            response.toResult(entityMapper = movieListDtoMapper)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override suspend fun getUpcomingMovies(page: Int): Result<MovieList> {
        return try {
            val response = moviesRemoteDataSource.getUpcomingMovies(page)
            response.toResult(entityMapper = movieListDtoMapper)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override suspend fun saveMovieToFavorites(movie: Movie): Result<Unit> {
        return try {
            moviesLocalDataSource.insertMovie(movieOrmMapper.mapToEntity(movie))
            return Result.Success(Unit)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override suspend fun deleteMovieFromFavorites(movie: Movie): Result<Unit> {
        return try {
            moviesLocalDataSource.deleteMovie(movieOrmMapper.mapToEntity(movie))
            return Result.Success(Unit)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }

    override fun getAllMoviesFromFavorites(): Result<Flow<List<Movie>>> {
        return try {
            val response = moviesLocalDataSource.getAllMovies().map {
                movieOrmMapper.mapFromEntityList(it)
            }
            return Result.Success(response)
        } catch (error: Exception) {
            Result.Error(UiText.DynamicString(error.message.toString()))
        }
    }
}