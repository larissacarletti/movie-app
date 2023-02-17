package com.example.movieapp.repository

import com.example.movieapp.BuildConfig
import com.example.movieapp.api.MovieAppApi
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import retrofit2.Response

class MovieRepository (
    private val movieApi: MovieAppApi,
) {

    private val apiKey: String = BuildConfig.API_KEY
    private val movieId: String = "299534"

   suspend fun getMovieRep(): Movie? {
       return movieApi.getMovie(movieId, apiKey).body()
    }
    suspend fun getSimilarMoviesRep(): SimilarMovies? {
        return movieApi.getSimilarMovie(movieId,apiKey).body()
    }
}