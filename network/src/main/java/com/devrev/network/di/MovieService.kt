package com.devrev.network.di

import com.devrev.network.di.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * @return List of [MovieResponse]
     * @param language the language to in which you want the data.
     * @param page the current page of items.
     */
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<List<MovieResponse>>
}