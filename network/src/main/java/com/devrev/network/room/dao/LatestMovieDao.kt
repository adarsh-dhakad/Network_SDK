package com.devrev.network.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devrev.network.room.LatestMovieEntity


@Dao
interface LatestMovieDao {
    @Upsert
    suspend fun insertLatestMovies(list: List<LatestMovieEntity>)

    @Query("SELECT * FROM LatestMovieEntity Order by createdAt ASC")
    fun getLatestMovies(): PagingSource<Int, LatestMovieEntity>

    @Query("DELETE FROM LatestMovieEntity")
    suspend fun clearAll()

}