package com.example.movieapp.repository

import com.example.movieapp.api.MovieAppApi
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import retrofit2.Response

class MovieRepository (
    private val movieApi: MovieAppApi,
) {

    private lateinit var apiKey: String
    private lateinit var movieId: String

   fun getMovieRep(): Response<Movie> {
       return movieApi.getMovie(movieId, apiKey)
    }
    fun getSimilarMoviesRep(): Response<SimilarMovies> {
        return movieApi.getSimilarMovie(movieId,apiKey)
    }
}