package com.example.mymovieskotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovieskotlin.model.apis.ApiInterface
import com.example.mymovieskotlin.data.Movies
import com.example.mymovieskotlin.databinding.ActivityMoviesBinding
import com.example.mymovieskotlin.view.adapters.CustomAdapter
import com.example.mymovieskotlin.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListenter{

    private lateinit var binding: ActivityMoviesBinding
    private lateinit var moviesAdapter: CustomAdapter
    private var moviesViewModel = MoviesViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)

        moviesViewModel.getMovies()
        initObservers()
    }

    private fun initObservers() {
        moviesViewModel.apply {
            movies.observe(this@MoviesActivity) {
                moviesAdapter = CustomAdapter(it, this@MoviesActivity)
                binding.recyclerViewMovies.adapter = moviesAdapter
            }
        }
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()
    }
    
}