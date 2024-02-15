package com.devrev.network.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "LatestMovieEntity")
data class LatestMovieEntity(
    @PrimaryKey
    val id: Int,
    val isAdultOnly: Boolean=false,
    val popularity: Double=0.0,
    val voteAverage: Double=0.0,
    val voteCount: Int=0,
    val image: String? = null,
    val backdropImage: String? = null,
    val poster_path:String? = null,
    val title: String? = null,
    val overview: String,
    val releaseDate: String? = null,
    val releaseDateAlternative: String? = null,
    val originalTitle: String? = null,
    val originalTitleAlternative: String? = null,
    val originalLanguage: String = "en-US",
    val nextKey:Int = 2,
    val createdAt:Int = 0
)