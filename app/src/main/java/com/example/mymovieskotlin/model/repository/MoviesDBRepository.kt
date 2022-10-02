package com.example.mymovieskotlin.model.repository

import com.example.mymovieskotlin.data.MovieDetails
import com.example.mymovieskotlin.data.Movies
import retrofit2.Call

interface MoviesDBRepository {
    fun getMovies(): Call<Movies>

    fun getMovieDetails(id: Int): Call<MovieDetails>
}