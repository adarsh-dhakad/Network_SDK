package com.devrev.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val viewModel:TopMoviesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("devKey", "01")
        viewModel.getTopMovies()
    }
}