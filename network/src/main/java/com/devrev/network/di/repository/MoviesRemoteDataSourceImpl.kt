package com.devrev.network.di.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devrev.network.di.Constants
import com.devrev.network.di.MovieService
import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseMovieDetails
import com.devrev.network.di.remote_data_source.MoviesPagingSource
import kotlinx.coroutines.flow.Flow

class MoviesRemoteDataSourceImpl(private val movieService:MovieService) : MoviesRemoteDataSource {

    override suspend fun getLatestMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = movieService)
            }
        ).flow
    }

//    override suspend fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
//       return null
//    }
//
//    override suspend fun getMovieDetails(): Flow<ResponseMovieDetails> {
//        TODO("Not yet implemented")
//    }
}