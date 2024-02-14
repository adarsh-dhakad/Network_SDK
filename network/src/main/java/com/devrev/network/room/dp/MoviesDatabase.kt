package com.devrev.network.room.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devrev.network.room.LatestMovieEntity
import com.devrev.network.room.dao.LatestMovieDao


@Database(entities = [LatestMovieEntity::class] , version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun getLatestDao(): LatestMovieDao
}