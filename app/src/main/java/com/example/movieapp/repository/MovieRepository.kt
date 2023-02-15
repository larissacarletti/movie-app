package com.example.movieapp.repository

import com.example.movieapp.api.MovieAppApi
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovie
import com.example.movieapp.model.similarmovies.SimilarMovies
import retrofit2.Response

class MovieRepository (
    private val movieApi: MovieAppApi,
    private val apiKey: String,
    private val movieId : String
) {
   fun getMovie(movie: Movie): Response<Movie> {
       return movieApi.getMovie(movieId, apiKey)
    }
    fun getSimilarMovies(similarMovie: SimilarMovie) : Response<SimilarMovies> {
        return movieApi.getSimilarMovie(movieId,apiKey)
    }
}