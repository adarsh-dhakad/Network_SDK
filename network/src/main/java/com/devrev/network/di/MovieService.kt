package com.devrev.network.di

import com.devrev.network.di.data.MovieResponse
import com.devrev.network.di.data.ResponseItems
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    /**
     * @return List of [MovieResponse]
     *
     * @param apiKey API KEY from TMDB. REPLACE this value with your KEY or add some authentication.
     * @param language the language to obtain the data.
     * @param page the current page of items.
     */
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResponseItems<MovieResponse>
}