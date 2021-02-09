package com.jedischool.assignment.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jedischool.assignment.R
import com.jedischool.assignment.data.model.Movie
import kotlinx.android.synthetic.main.movie_row_item.view.*

class MainAdapter(private val movies: ArrayList<Movie>, private val context: Context, private val movieClicked: MovieClicked) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    interface MovieClicked {
        fun onMovieClicked(pos: Int)
    }

    class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie : Movie)
        {
            itemView.apply {
                movie_title.text = movie.Title
                movie_desc.text = movie.Type
                movie_year.text = movie.Year
                Glide.with(movie_poster.context)
                    .load(movie.Poster)
                    .into(movie_poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_row_item, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            movieClicked.onMovieClicked(position)
        }
    }

    fun addMovies(movies : List<Movie>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
    }
}