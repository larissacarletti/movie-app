package com.example.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.adapter.SimilarMovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.viewmodel.MovieViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MovieViewModel>()
    private lateinit var similarMovieAdapter: SimilarMovieAdapter
    private lateinit var dataStore : DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataStore = preferencesDataStore("settings")

        binding.favoriteButton.setOnClickListener {
            lifecycleScope.launch {

            }
        }


        showMovie()
        showSimiliarMovies()
    }

    private suspend fun save(key: String, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }




    private fun setRecyclerView() = binding.run {
        similarMovieAdapter = SimilarMovieAdapter()
        recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = similarMovieAdapter

    }
    private fun setMovieInformation(movie: Movie) = binding.run {
        movieTitle.text = movie.title
        movieDescription.text = movie.overview
        movieGenre.text = "${movie.genres.map {  genre -> genre.name }}"
        likeCount.text = getString(R.string.like_count,movie.voteCount.toString())
        viewsCount.text= getString(R.string.popularity_count,movie.popularity.toString())
        Glide.with(movieBanner)
            .load("https://image.tmdb.org/t/p/original/${movie.posterPath}")
            .into(movieBanner)
    }

    private fun showMovie() = binding.run {
        progressBar.visibility = View.VISIBLE
        viewModel.movie.observe(this@MainActivity) {
            setMovieInformation(it)
            progressBar.visibility = View.INVISIBLE
        }
    }

    private fun showSimiliarMovies() {
        setRecyclerView()
        viewModel.similarMovies.observe(this) {
            similarMovieAdapter.getSimilarMoviesList(it)
        }
    }
}