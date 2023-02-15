package com.example.movieapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovie
import com.example.movieapp.repository.MovieRepository

class MovieViewModel (private val repository: MovieRepository) : ViewModel() {

    fun getMovieInformation(movie: Movie) {
        repository.getMovie(movie)
    }

    fun getSimilarMovieInformation(similarMovie: SimilarMovie) {
        repository.getSimilarMovies(similarMovie)
    }
}