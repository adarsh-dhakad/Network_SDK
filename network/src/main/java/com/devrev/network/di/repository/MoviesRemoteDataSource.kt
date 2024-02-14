package com.devrev.network.di.repository

import androidx.paging.PagingData
import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseMovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    suspend fun getLatestMovies(): Flow<PagingData<MovieResponse>>
//    suspend fun getPopularMovies(): Flow<PagingData<MovieResponse>>
//
//    suspend fun getMovieDetails():Flow<ResponseMovieDetails>
}