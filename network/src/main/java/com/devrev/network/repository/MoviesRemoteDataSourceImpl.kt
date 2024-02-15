package com.devrev.network.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devrev.network.Constants
import com.devrev.network.api.MovieService
import com.devrev.network.data.MovieResponse
import com.devrev.network.data.ResponseMovieDetails
import com.devrev.network.remote_mediator.LatestMovieRemoteMediator
import com.devrev.network.remote_mediator.PopularMovieRemoteMediator
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.dp.MoviesDatabase
import com.devrev.network.room.entity.PopularMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MoviesRemoteDataSourceImpl(private val movieService: MovieService,private val roomDB:MoviesDatabase) : MoviesRemoteDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getLatestMovies(): Flow<PagingData<LatestMovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 10,
                maxSize = 40,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
              //  MoviesPagingSource(service = movieService)
               roomDB.getLatestMovieDao().getLatestMovies()
            },
            remoteMediator =  LatestMovieRemoteMediator(roomDB,movieService)
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPopularMovies(): Flow<PagingData<PopularMovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 10,
                maxSize = 40,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                //  MoviesPagingSource(service = movieService)
                roomDB.getPopularMovieDao().getPopularMovies()
            },
            remoteMediator =  PopularMovieRemoteMediator(roomDB,movieService)
        ).flow
    }

    override suspend fun getMovieDetails(movieId:Int): Response<ResponseMovieDetails> {
        return movieService.getMovieDetails(movieId,"en-US")
    }
}