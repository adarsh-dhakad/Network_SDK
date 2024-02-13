package com.devrev.network.di.repository

import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseItems
import java.util.concurrent.Flow

interface MoviesRemoteDataSource {
    suspend fun getMovies():ResponseItems<MovieResponse>
}