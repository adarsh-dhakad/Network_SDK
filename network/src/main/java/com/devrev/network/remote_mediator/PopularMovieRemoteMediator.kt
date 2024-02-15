package com.devrev.network.remote_mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.devrev.network.api.MovieService
import com.devrev.network.mapper.toLatestMovieEntity
import com.devrev.network.mapper.toPopularMovieEntity
import com.devrev.network.room.entity.LatestMovieEntity
import com.devrev.network.room.dp.MoviesDatabase
import com.devrev.network.room.entity.PopularMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularMovieRemoteMediator(
    private val db: MoviesDatabase, private val apiService: MovieService
) : RemoteMediator<Int, PopularMovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, PopularMovieEntity>
    ): MediatorResult {

        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                1
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val page = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
                    ?.let { it.nextKey }
                page ?: return MediatorResult.Success(endOfPaginationReached = false)
            }
        }

        try {
            val response = apiService.getPopularMovies("en-US", page)

            val movies = ArrayList<PopularMovieEntity>()
            for (i in response.body()?.results?.indices ?: 0..0) {
                response.body()?.results?.get(i)?.toPopularMovieEntity(page, i)?.let {
                    movies.add(it)
                }
            }

            val endList = (response.body()?.results?.isEmpty()) ?: true

            withContext(Dispatchers.IO) {
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        db.getPopularMovieDao().clearAll()
                    }
                    db.getPopularMovieDao().insertPopularMovies(movies)
                }
            }

            return MediatorResult.Success(endList)
        } catch (e: IOException) {
            Log.e("RemoteMediator123", "IOException", e)
            return MediatorResult.Error(e)
        } catch (e: retrofit2.HttpException) {
            Log.e("RemoteMediator123", "HttpException", e)
            return MediatorResult.Error(e)
        }

    }
}