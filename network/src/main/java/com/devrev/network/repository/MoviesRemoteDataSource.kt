package com.devrev.network.repository

import androidx.paging.PagingData
import com.devrev.network.data.MovieResponse
import com.devrev.network.data.ResponseMovieDetails
import com.devrev.network.room.LatestMovieEntity
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    suspend fun getLatestMovies(): Flow<PagingData<LatestMovieEntity>>
//    suspend fun getPopularMovies(): Flow<PagingData<MovieResponse>>
//
//    suspend fun getMovieDetails():Flow<ResponseMovieDetails>
}