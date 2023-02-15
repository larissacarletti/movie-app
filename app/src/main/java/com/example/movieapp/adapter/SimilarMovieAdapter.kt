package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.MainActivity
import com.example.movieapp.databinding.ListItemBinding
import com.example.movieapp.model.similarmovies.SimilarMovie

class SimilarMovieAdapter (listener: MainActivity): RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    private var similarMovieList = ArrayList<SimilarMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieAdapter.SimilarMovieViewHolder, position: Int) {
        holder.run {
            Glide.with(similarMoviePoster)
                .load("https://image.tmdb.org/t/p/original/${similarMovieList[position].posterPath}")
                .into(similarMoviePoster)
            similarMovieTitle.text = similarMovieList[position].title
            similarMovieYear.text = similarMovieList[position].releaseDate.substring(0,4)
        }
    }

    override fun getItemCount(): Int = similarMovieList.size

    inner class SimilarMovieViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val similarMoviePoster = binding.similarMovieBanner
        val similarMovieTitle = binding.similarMovieTitle
        val similarMovieYear = binding.releaseYearSimilarMovie
    }
}
