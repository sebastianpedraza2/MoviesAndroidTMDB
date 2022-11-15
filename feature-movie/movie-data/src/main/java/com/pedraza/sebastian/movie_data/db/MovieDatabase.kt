package com.pedraza.sebastian.movie_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedraza.sebastian.movie_data.entities.orm.MovieOrm

@Database(
    entities = [MovieOrm::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}