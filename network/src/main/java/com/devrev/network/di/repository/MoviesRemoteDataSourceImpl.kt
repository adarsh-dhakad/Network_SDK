package com.devrev.network.di.repository

import com.devrev.network.di.MovieService
import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseItems

class MoviesRemoteDataSourceImpl(val api:MovieService) : MoviesRemoteDataSource {
  override suspend fun getMovies(): ResponseItems<MovieResponse> {
      return api.getTopRatedMovies("en" , 10)
  }
}