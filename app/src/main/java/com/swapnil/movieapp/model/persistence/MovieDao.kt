package com.swapnil.movieapp.model.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swapnil.movieapp.model.persistence.data.MovieDetailEntity
import com.swapnil.movielistapp.model.persistence.data.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MoviesDb")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM MoviesDb")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM MovieDetailsDb WHERE movieDetailId = :movieId")
    fun getMovieDetails(movieId: Int) : Flow<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(detail: MovieDetailEntity)

}