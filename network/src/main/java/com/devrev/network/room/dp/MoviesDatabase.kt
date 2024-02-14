package com.devrev.network.room.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.dao.LatestMovieDao
import com.devrev.network.room.dao.PopularMovieDao
import com.devrev.network.room.entity.PopularMovieEntity


@Database(entities = [LatestMovieEntity::class,PopularMovieEntity::class] , version = 2, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getLatestMovieDao(): LatestMovieDao

    abstract fun getPopularMovieDao():PopularMovieDao
}