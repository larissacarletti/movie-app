package com.example.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.adapter.SimilarMovieAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.model.movie.Movie
import com.example.movieapp.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MovieViewModel>()
    private lateinit var similarMovieAdapter: SimilarMovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showMovie()
        showSimiliarMovies()
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
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun showSimiliarMovies() {
        setRecyclerView()
        viewModel.similarMovies.observe(this) {
            similarMovieAdapter.getSimilarMoviesList(it)
        }
    }
}