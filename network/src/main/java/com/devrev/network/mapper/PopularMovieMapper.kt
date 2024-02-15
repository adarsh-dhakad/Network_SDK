package com.devrev.network.mapper

import com.devrev.network.Constants
import com.devrev.network.data.MovieResponse
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.entity.PopularMovieEntity

fun MovieResponse.toPopularMovieEntity(page:Int, index:Int): PopularMovieEntity {
    return PopularMovieEntity(
        id = id,
        voteAverage?:0.0,
        poster_path,
        title,
        nextKey = page+1,
        (((page)* Constants.PAGE_SIZE)+index)
    )
}