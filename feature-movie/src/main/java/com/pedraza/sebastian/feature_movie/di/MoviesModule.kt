package com.pedraza.sebastian.feature_movie.di

import android.app.Application
import androidx.room.Room
import com.pedraza.sebastian.movie_data.api.MoviesService
import com.pedraza.sebastian.movie_data.db.MovieDao
import com.pedraza.sebastian.movie_data.db.MovieDatabase
import com.pedraza.sebastian.movie_data.mappers.MovieDetailDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieOrmMapper
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
}