package com.example.movieapp.di

import com.example.movieapp.api.MovieAppApi
import com.example.movieapp.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAppApi::class.java)
    }

    single {MovieRepository(get())}








}