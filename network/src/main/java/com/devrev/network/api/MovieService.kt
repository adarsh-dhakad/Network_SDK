package com.devrev.network.api

import com.devrev.network.data.MovieResponse
import com.devrev.network.data.ResponseItems
import com.devrev.network.data.ResponseMovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    /**
     * Fetches details for a specific list of movie
     * @param language the language to in which you want the data.
     * @param page the current page of items.
     *  @return List of [MovieResponse]
     */
    @GET("movie/now_playing")
    suspend fun getLatestMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ResponseItems<MovieResponse>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ResponseItems<MovieResponse>>

    /**
     * Fetches details for a specific movie.
     * @param movie_id The ID of the movie for which you want to retrieve data.
     * @param language The language in which you want the data to be returned.
     * @return [ResponseMovieDetails] containing details of the specified movie.
     */
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String
    ): Response<ResponseMovieDetails>

}