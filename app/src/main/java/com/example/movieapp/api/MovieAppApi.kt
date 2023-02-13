package com.example.movieapp.api

import retrofit2.Response

interface MovieAppApi {

    fun getMovieInformation(): Response
}