package com.devrev.network.remote_data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.devrev.network.Constants.PAGE_SIZE
import com.devrev.network.api.MovieService
import com.devrev.network.data.MovieResponse
import com.devrev.network.mapper.toLatestMovieEntity
import com.devrev.network.room.LatestMovieEntity
import java.io.IOException

class MoviesPagingSource(
    private val service: MovieService
) : PagingSource<Int, LatestMovieEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LatestMovieEntity> {
        val pageIndex = params.key ?: 1
        return try {
            /**
             * I am harding coding language but we can use request parameters
             */
            val response = service.getLatestMovies(
                language = "en-US",
                page = pageIndex
            )
            val movies = response.body()?.results?.map {
                it.toLatestMovieEntity(0,1)
            }?:ArrayList()

            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    pageIndex + (params.loadSize / PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LatestMovieEntity>): Int? {
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}