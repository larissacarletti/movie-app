package com.example.movieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (repository: MovieRepository) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    private val _similarMovies = MutableLiveData<SimilarMovies>()
    val movie: LiveData<Movie> = _movie
    val similarMovies: LiveData<SimilarMovies> = _similarMovies

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(repository.getMovieRep())
            _similarMovies.postValue(repository.getSimilarMoviesRep())
        }
    }

}
