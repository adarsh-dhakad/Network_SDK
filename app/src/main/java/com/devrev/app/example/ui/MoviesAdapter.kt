package com.devrev.app.example.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.devrev.app.databinding.DashboardMovieItemBinding

/*
*  this activity is created for testing
*
* */
class MoviesAdapter : PagingDataAdapter<MovieUi, MoviesAdapter.MoviePosterViewHolder>(
    MovieDiffCallBack()
) {

    class MoviePosterViewHolder(val binding: DashboardMovieItemBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(path: MovieUi?) {
            path?.let {
                binding.tv.text = "${it.title}"
                binding.ivMoviePoster.visibility = View.VISIBLE
                binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/${it.image}") {
                    crossfade(durationMillis = 2000)
                    transformations(RoundedCornersTransformation(12.5f))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val holder = MoviePosterViewHolder(
            DashboardMovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        return holder
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
      //  Log.d("devkey" , "view holder"+getItem(position).toString())
        holder.bind(getItem(position))
    }

}

private class  MovieDiffCallBack : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }
}