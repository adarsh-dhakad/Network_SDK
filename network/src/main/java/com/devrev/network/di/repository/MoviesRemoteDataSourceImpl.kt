package com.devrev.network.di.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devrev.network.di.Constants
import com.devrev.network.di.MovieService
import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.remote_data_source.MoviesPagingSource
import kotlinx.coroutines.flow.Flow

class MoviesRemoteDataSourceImpl(private val movieService:MovieService) : MoviesRemoteDataSource {
  override suspend fun getMovies(): Flow<PagingData<MovieResponse>> {
      return Pager(
          config = PagingConfig(
              pageSize = Constants.PAGE_SIZE,
              enablePlaceholders = false
          ),
          pagingSourceFactory = {
              MoviesPagingSource(service = movieService)
          }
      ).flow
  }
}