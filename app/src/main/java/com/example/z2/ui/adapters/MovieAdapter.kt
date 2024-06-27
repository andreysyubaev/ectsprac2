package com.example.z2.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.z2.databinding.MovieItemBinding
import com.example.z2.ui.models.Movie

class MovieAdapter private constructor(context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var selectedPosition: Int = -1
    private var selectedMovie: MovieAdapter? = null
    private lateinit var items: List<Movie>

    companion object Factory{
        fun create(context: Context): MovieAdapter{
            return MovieAdapter(context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        /*return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )*/

        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.sortedByDescending { it.rating }[position]
        holder.tvMovieName.text = item.movie
        holder.tvRating.text = item.rating.toString()
    }

    override fun getItemCount(): Int{
        return if (::items.isInitialized) {
            items.size
        } else {
            0
        }
    }

    fun getSelectedCourier(): MovieAdapter?{
        return selectedMovie
    }

    class ViewHolder(binding: MovieItemBinding) :RecyclerView.ViewHolder(binding.root){
        val tvMovieName = binding.movieName
        val tvRating = binding.movieRating
    }

    fun refreshMovies(items: List<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }
}