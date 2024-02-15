package com.devrev.network.mapper

import com.devrev.network.Constants
import com.devrev.network.data.MovieResponse
import com.devrev.network.room.entity.LatestMovieEntity

fun MovieResponse.toLatestMovieEntity(page:Int,index:Int): LatestMovieEntity {
    return LatestMovieEntity(
        id = id,
        isAdultOnly?:false,
        popularity?:0.0,
        voteAverage?:0.0,
        voteCount?:0,
        image,
        backdropImage,
        poster_path,
        title,
        overview?:"",
        releaseDate,
        releaseDateAlternative?:"",
        originalTitle?:"",
        originalTitleAlternative?:"",
        originalLanguage?:"en-US",
        nextKey = page+1,
        (((page)*Constants.PAGE_SIZE)+index)
    )
}