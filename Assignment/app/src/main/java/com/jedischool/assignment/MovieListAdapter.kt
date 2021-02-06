package com.jedischool.assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter(private val list: ArrayList<Movie>, private val context: Context, private val movieClicked: MovieClicked) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    interface MovieClicked {
        fun onMovieClicked(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list[position].poster).into(holder.mPoster)
        holder.mTitle.text = list[position].title
        holder.mYear.text = list[position].year.toString()
        holder.mDesc.text = list[position].description
        holder.itemView.setOnClickListener {
            movieClicked.onMovieClicked(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mPoster: ImageView = itemView.findViewById(R.id.movie_poster)
        val mTitle: TextView = itemView.findViewById(R.id.movie_title)
        val mYear: TextView = itemView.findViewById(R.id.movie_year)
        val mDesc: TextView = itemView.findViewById(R.id.movie_desc)
    }
}