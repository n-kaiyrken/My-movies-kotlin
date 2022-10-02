package com.example.mymovieskotlin.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.mymovieskotlin.data.MovieDetails
import com.example.mymovieskotlin.data.Movies
import com.example.mymovieskotlin.data.Result
import com.example.mymovieskotlin.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository{

    override fun getMovies(): Call<Movies> {
        return ApiInterface.create().getMovies("ab32d14ff19656193459afda4ed81488")
    }

    override fun getMovieDetails(id :Int): Call<MovieDetails> {
        return ApiInterface.create().getMovieDetails(id,"ab32d14ff19656193459afda4ed81488")
    }

}