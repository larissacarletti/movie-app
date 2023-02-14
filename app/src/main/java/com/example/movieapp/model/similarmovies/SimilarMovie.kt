package com.example.movieapp.model.similarmovies


import com.google.gson.annotations.SerializedName

data class SimilarMovie(
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
)