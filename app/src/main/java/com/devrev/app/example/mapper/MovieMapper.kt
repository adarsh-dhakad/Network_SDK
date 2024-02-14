package com.devrev.app.example.mapper


import com.devrev.app.example.ui.MovieUi
import com.devrev.network.room.entity.LatestMovieEntity

/*
*  this class is created for testing
*
* */
object MovieMapper{

    suspend fun mapDomainMovieToUi(
        domainMovie: LatestMovieEntity
    ): MovieUi {
        return MovieUi(
            id = domainMovie.id?:0,
            isAdultOnly = domainMovie.isAdultOnly?:false,
            popularity = domainMovie.popularity?:0.0,
            voteAverage = domainMovie.voteAverage?:0.0,
            voteCount = domainMovie.voteCount?:0,
            image = domainMovie.poster_path?:"",
            title = domainMovie.title?:"",
            overview = domainMovie.overview?:"",
            releaseDate = domainMovie.releaseDate?:"",
            originalLanguage = domainMovie.originalLanguage?:""
        )
    }

}