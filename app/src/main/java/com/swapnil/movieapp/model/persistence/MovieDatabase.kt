package com.swapnil.movielistapp.model.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.swapnil.movieapp.model.persistence.MovieDao
import com.swapnil.movielistapp.model.persistence.data.MovieEntity

@Database(entities = [MovieEntity::class],
    version = RoomConstants.MOVIE_APP_DB_VERSION,
    exportSchema = false
)
abstract class MovieDatabase():RoomDatabase(){

    abstract fun movieDao() : MovieDao

}