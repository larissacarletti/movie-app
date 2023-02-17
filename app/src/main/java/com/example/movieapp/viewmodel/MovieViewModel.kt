package com.example.movieapp.viewmodel

import androidx.lifecycle.*
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (repository: MovieRepository) : ViewModel() {

    val movie : LiveData<Movie> = liveData { repository.getMovieRep() }
    val similarMovies: LiveData<SimilarMovies> = liveData { repository.getSimilarMoviesRep() }

}
