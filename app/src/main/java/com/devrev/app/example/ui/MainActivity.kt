package com.devrev.app.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrev.app.TopMoviesViewModel
import com.devrev.app.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
*  this activity is created for testing
*
* */
class MainActivity : AppCompatActivity() {
    val moviesViewModel: TopMoviesViewModel by viewModel()
    private var adapter: MoviesAdapter? = null
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        collectUiState()

    }

    private fun initView() {


//        binding.rvMovies.adapter = adapter?.withLoadStateHeaderAndFooter(
//            header = MovieLoadStateAdapter { adapter?.retry() },
//            footer = MovieLoadStateAdapter { adapter?.retry() }
//        )

    //    adapter?.addLoadStateListener { loadState -> renderUi(loadState) }

   //     binding.btnMoviesRetry.setOnClickListener { adapter?.retry() }

    }

    private fun collectUiState() {
        adapter = MoviesAdapter()
        binding.rvMovies.adapter = adapter
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        GlobalScope.launch {
            val list = listOf<MovieUi>(
                MovieUi(),
                MovieUi(),
                MovieUi(),
                MovieUi(),
                MovieUi(),
                MovieUi(),
                MovieUi()
            )
            adapter?.submitData(PagingData.from(list))
            moviesViewModel.getTopMovies().collectLatest { movies ->
                movies.map {
                    Log.d("devKey" , "rr"+it.toString())
                }
                adapter?.submitData(movies)
                Log.d("devkey" , "item count"+adapter?.itemCount.toString())
            }
        }
    }

    private fun renderUi(loadState: CombinedLoadStates) {

        Log.d("devkey" , loadState.source.toString())
        val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter?.itemCount == 0

        binding.rvMovies.isVisible = !isListEmpty
     //   binding.tvMoviesEmpty.isVisible = isListEmpty

        // Only shows the list if refresh succeeds.
        binding.rvMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        binding.progressBarMovies.isVisible = loadState.source.refresh is LoadState.Loading
        // Show the retry state if initial load or refresh fails.
    //    binding.btnMoviesRetry.isVisible = loadState.source.refresh is LoadState.Error
    }

}