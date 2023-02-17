package com.example.movieapp.api

import com.example.movieapp.model.movie.Movie
import com.example.movieapp.model.similarmovies.SimilarMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAppApi {
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<Movie>

    @GET("/movie/{movie_id}/similar")
    suspend fun getSimilarMovie(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): Response<SimilarMovies>
}