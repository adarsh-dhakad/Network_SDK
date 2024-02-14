package com.devrev.network.repository

import androidx.paging.PagingData
import com.devrev.network.data.MovieResponse
import com.devrev.network.data.ResponseMovieDetails
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.entity.PopularMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getLatestMovies(): Flow<PagingData<LatestMovieEntity>>
    suspend fun getPopularMovies(): Flow<PagingData<PopularMovieEntity>>

    suspend fun getMovieDetails(movieId:Int): Response<ResponseMovieDetails>
}