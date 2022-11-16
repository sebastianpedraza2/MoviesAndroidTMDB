package com.pedraza.sebastian.feature_movie.di

import android.app.Application
import androidx.room.Room
import com.pedraza.sebastian.feature_movie.BuildConfig
import com.pedraza.sebastian.movie_data.api.MoviesService
import com.pedraza.sebastian.movie_data.datasource.local.MoviesLocalDataSource
import com.pedraza.sebastian.movie_data.datasource.local.MoviesLocalDataSourceImpl
import com.pedraza.sebastian.movie_data.datasource.remote.MoviesRemoteDataSource
import com.pedraza.sebastian.movie_data.datasource.remote.MoviesRemoteDataSourceImpl
import com.pedraza.sebastian.movie_data.db.MovieDao
import com.pedraza.sebastian.movie_data.db.MovieDatabase
import com.pedraza.sebastian.movie_data.mappers.MovieDetailDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieListDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieOrmMapper
import com.pedraza.sebastian.movie_data.repositories.MoviesRepositoryImpl
import com.pedraza.sebastian.movie_domain.repositories.MoviesRepository
import com.pedraza.sebastian.movie_domain.usecases.favorites.*
import com.pedraza.sebastian.movie_domain.usecases.movie_detail.GetMovieDetailUseCase
import com.pedraza.sebastian.movie_domain.usecases.movie_detail.GetMovieDetailUseCaseImpl
import com.pedraza.sebastian.movie_domain.usecases.movie_list.GetMovieListUseCase
import com.pedraza.sebastian.movie_domain.usecases.movie_list.GetMovieListUseCaseImpl
import com.pedraza.sebastian.movie_domain.usecases.top_rated_movies.GetTopRatedMovieListUseCase
import com.pedraza.sebastian.movie_domain.usecases.top_rated_movies.GetTopRatedMovieListUseCaseImpl
import com.pedraza.sebastian.movie_domain.usecases.upcoming_movies.GetUpcomingMovieListUseCase
import com.pedraza.sebastian.movie_domain.usecases.upcoming_movies.GetUpcomingMovieListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMovieDatabase(context: Application): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    @Provides
    fun provideMovieDtoMapper(): MovieDtoMapper = MovieDtoMapper()

    @Provides
    fun provideMovieORMMapper(): MovieOrmMapper = MovieOrmMapper()

    @Provides
    fun provideMovieDetailMapper(): MovieDetailDtoMapper = MovieDetailDtoMapper()

    @Provides
    fun provideMovieListDtoMapper(movieDtoMapper: MovieDtoMapper): MovieListDtoMapper =
        MovieListDtoMapper(movieDtoMapper)

    @Provides
    fun provideMoviesLocalDataSource(movieDao: MovieDao): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(movieDao)

    @Provides
    fun provideMoviesRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(moviesService, BuildConfig.API_KEY)

    @Singleton
    @Provides
    fun providesMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource,
        movieListDtoMapper: MovieListDtoMapper,
        movieDetailDtoMapper: MovieDetailDtoMapper,
        movieOrmMapper: MovieOrmMapper
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesRemoteDataSource,
        moviesLocalDataSource,
        movieListDtoMapper,
        movieDetailDtoMapper,
        movieOrmMapper
    )

    @Provides
    fun provideGetMovieDetailUseCase(repository: MoviesRepository): GetMovieDetailUseCase =
        GetMovieDetailUseCaseImpl(repository)

    @Provides
    fun provideSaveMovieToFavoritesUseCase(repository: MoviesRepository): SaveMovieToFavoritesUseCase =
        SaveMovieToFavoritesUseCaseImpl(repository)

    @Provides
    fun provideDeleteMovieFromFavoritesUseCase(repository: MoviesRepository): DeleteMovieFromFavoritesUseCase =
        DeleteMovieFromFavoritesUseCaseImpl(repository)

    @Provides
    fun provideGetAllMoviesFromFavoritesUseCase(repository: MoviesRepository): GetAllMoviesFromFavoritesUseCase =
        GetAllMoviesFromFavoritesUseCaseImpl(repository)

    @Provides
    fun provideGetMovieListUseCase(repository: MoviesRepository): GetMovieListUseCase =
        GetMovieListUseCaseImpl(repository)

    @Provides
    fun provideGetTopRatedMovieListUseCase(repository: MoviesRepository): GetTopRatedMovieListUseCase =
        GetTopRatedMovieListUseCaseImpl(repository)

    @Provides
    fun provideGetUpcomingMovieListUseCase(repository: MoviesRepository): GetUpcomingMovieListUseCase =
        GetUpcomingMovieListUseCaseImpl(repository)
}