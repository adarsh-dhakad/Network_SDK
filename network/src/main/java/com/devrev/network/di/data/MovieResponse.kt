package com.devrev.network.di.data

data class MovieResponse(
    val id: Int,
    val isAdultOnly: Boolean,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val image: String? = null,
    val backdropImage: String? = null,
    val title: String? = null,
    val overview: String,
    val releaseDate: String? = null,
    val releaseDateAlternative: String? = null,
    val originalTitle: String? = null,
    val originalTitleAlternative: String? = null,
    val originalLanguage: String
)