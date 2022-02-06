package com.swapnil.movieapp.model.persistence.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swapnil.movielistapp.model.persistence.RoomConstants

@Entity(tableName = RoomConstants.MOVIE_DETAILS_DB_TABLE)
data class MovieDetailEntity(
    @PrimaryKey val movieDetailId: Int,
    val original_title: String,
    val overview: String,
    val posterPath: String,
    val releasedDate: String
)
