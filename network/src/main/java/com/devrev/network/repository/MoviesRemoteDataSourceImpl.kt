package com.devrev.network.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devrev.network.Constants
import com.devrev.network.api.MovieService
import com.devrev.network.data.MovieResponse
import com.devrev.network.data.ResponseMovieDetails
import com.devrev.network.remote_data_source.MoviesPagingSource
import com.devrev.network.remote_mediator.LatestMovieRemoteMediator
import com.devrev.network.room.LatestMovieEntity
import com.devrev.network.room.dp.MoviesDatabase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MoviesRemoteDataSourceImpl(private val movieService: MovieService,private val roomDB:MoviesDatabase) : MoviesRemoteDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getLatestMovies(): Flow<PagingData<LatestMovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
              //  MoviesPagingSource(service = movieService)
               roomDB.getLatestDao().getLatestMovies()
            },
            remoteMediator =  LatestMovieRemoteMediator(roomDB,movieService)
        ).flow
    }

//    override suspend fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
//       return null
//    }
//
//    override suspend fun getMovieDetails(movieId:Int): Response<ResponseMovieDetails> {
//        return movieService.getMovieDetails(movieId)
//    }
}