package com.devrev.network.mapper

import com.devrev.network.Constants
import com.devrev.network.data.MovieResponse
import com.devrev.network.room.entity.LatestMovieEntity

fun MovieResponse.toLatestMovieEntity(page:Int,index:Int): LatestMovieEntity {
    return LatestMovieEntity(
        id = id,
        voteAverage=voteAverage?:0.0,
        poster_path=poster_path,
        title=title,
        nextKey = page+1,
        (((page)*Constants.PAGE_SIZE)+index)
    )
}