package com.devrev.app.example.ui

data class MovieUi(
    val id: Int = 0,
    val isAdultOnly: Boolean = false,
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val image: String = "",
    val title: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val originalLanguage: String = ""
)