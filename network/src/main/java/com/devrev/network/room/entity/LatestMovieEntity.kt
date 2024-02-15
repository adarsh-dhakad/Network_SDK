package com.devrev.network.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "LatestMovieEntity")
data class LatestMovieEntity(
    @PrimaryKey
    val id: Int,
    val voteAverage: Double=0.0,
    val poster_path:String? = null,
    val title: String? = null,
    val nextKey:Int = 2,
    val createdAt:Int = 0
)