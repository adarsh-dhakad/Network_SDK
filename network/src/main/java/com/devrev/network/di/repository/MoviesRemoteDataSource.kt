package com.devrev.network.di.repository

import androidx.paging.PagingData
import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseItems
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    suspend fun getMovies(): Flow<PagingData<MovieResponse>>
}