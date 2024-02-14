package com.devrev.network.data

sealed class MovieDetailsResponse<out T> {

    class Success<out T>(val data: T) : MovieDetailsResponse<T>()
    class Error(val message: String) : MovieDetailsResponse<Nothing>()
    object Loading : MovieDetailsResponse<Nothing>()
}