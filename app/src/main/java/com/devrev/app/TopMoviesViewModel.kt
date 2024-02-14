package com.devrev.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.devrev.app.example.mapper.MovieMapper
import com.devrev.app.example.ui.MovieUi
import com.devrev.network.di.repository.MoviesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TopMoviesViewModel(private val moviesRemoteDataSource: MoviesRemoteDataSource) : ViewModel() {

    suspend fun getTopMovies(): Flow<PagingData<MovieUi>> {
       return moviesRemoteDataSource.getMovies().map{
                pagingData ->
             pagingData.map {
                 MovieMapper.mapDomainMovieToUi(it)
             }

        }.cachedIn(viewModelScope)

    }
}