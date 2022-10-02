package com.example.mymovieskotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mymovieskotlin.model.apis.ApiInterface
import com.example.mymovieskotlin.data.MovieDetails
import com.example.mymovieskotlin.databinding.ActivityMovieDetailsBinding
import com.example.mymovieskotlin.view.adapters.CustomAdapter
import com.example.mymovieskotlin.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private val movieViewModel = MoviesViewModel()

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val id = intent.getIntExtra("id", 0)
        initObservers()
        movieViewModel.getMovieDetails(id)

    }

    private fun initObservers() {
        movieViewModel.apply {
            movieDetails.observe(this@MovieDetailsActivity) {
                binding.movieDetailsTextViewTitle.text = it.title
                Picasso.get().load("https://image.tmdb.org/t/p/w500/${it.poster_path}").into(binding.movieDetailsImageViewBanner)
                binding.movieDetailsBodyReleaseDate.text = it.release_date
                binding.movieDetailsBodyScore.text = it.vote_average.toString()
                binding.movieDetailsBodyOverview.text = it.overview
            }
        }
    }
}