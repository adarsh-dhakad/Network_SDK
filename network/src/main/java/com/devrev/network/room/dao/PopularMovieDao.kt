package com.devrev.network.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.entity.PopularMovieEntity

@Dao
interface PopularMovieDao {
    @Upsert
    suspend fun insertPopularMovies(list: List<PopularMovieEntity>)

    @Query("SELECT * FROM PopularMovieEntity Order by createdAt ASC")
    fun getPopularMovies(): PagingSource<Int, PopularMovieEntity>

    @Query("DELETE FROM PopularMovieEntity")
    suspend fun clearAll()
}