package com.pedraza.sebastian.movie_data.db

import androidx.room.*
import com.pedraza.sebastian.movie_data.entities.orm.MovieOrm
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieOrm: MovieOrm)

    @Delete
    suspend fun deleteMovie(movieOrm: MovieOrm)

    @Query(
        """
        SELECT *
        FROM movies
    """
    )
    fun getAllMovies(): Flow<List<MovieOrm>>
}