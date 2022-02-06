package com.swapnil.movielistapp.model.persistence.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.swapnil.movielistapp.model.persistence.RoomConstants

@Entity(tableName = RoomConstants.MOVIES_DB_TABLE)
data class MovieEntity(
    @PrimaryKey val id: Int,
    val backDropPath: String,
    val ogLang: String,
    val ogTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val votesRating: Double,
    val votesCount: Int
)
