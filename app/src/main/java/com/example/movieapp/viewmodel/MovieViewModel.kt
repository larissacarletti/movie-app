package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.api.MovieAppApi
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (private val repository: MovieRepository, private val movieAppApi: MovieAppApi) : ViewModel() {

    private val movie = repository.getMovieRep().body()!!.asLiveData()

    fun getMovieInformation() : LiveData<Movie> {
        repository.getMovieRep().body()!!.asLiveData()
    }



}
