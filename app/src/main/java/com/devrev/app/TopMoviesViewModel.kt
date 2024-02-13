package com.devrev.app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrev.network.di.repository.MoviesRemoteDataSource
import kotlinx.coroutines.launch

class TopMoviesViewModel(val moviesRemoteDataSource: MoviesRemoteDataSource) : ViewModel() {

    fun getTopMovies(){
        viewModelScope.launch {
            val result = moviesRemoteDataSource.getMovies()
            Log.d("devKey", result.toString())
        }
    }
}