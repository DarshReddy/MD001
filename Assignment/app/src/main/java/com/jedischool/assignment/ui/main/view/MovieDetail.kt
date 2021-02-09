package com.jedischool.assignment.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.jedischool.assignment.R
import com.jedischool.assignment.data.api.ApiHelper
import com.jedischool.assignment.data.api.RetrofitBuilder
import com.jedischool.assignment.data.model.MovieDetails
import com.jedischool.assignment.ui.base.ViewModelFactory
import com.jedischool.assignment.ui.main.viewmodel.MainViewModel
import com.jedischool.assignment.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_row_item.*

class MovieDetail: AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var movie: MovieDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val imdbID = intent.getStringExtra("movie_id")

        setupViewModel()
        if (imdbID != null) {
            setupObservers(imdbID)
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers(imdbID:String) {
        viewModel.getMovie(imdbID).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        detail_progress.visibility = View.GONE
                        resource.data?.let { response -> setMovie(response) }
                    }
                    Status.ERROR -> {
                        detail_progress.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        detail_progress.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setMovie(movie: MovieDetails) {
        this.movie = movie
        Glide.with(this)
                .load(movie.Poster)
                .into(movie_background)
        title_detail.text = movie.Title
        movie_rating.text = "IMDB rating: " + movie.imdbRating
        rating_bar.progress = (movie.imdbRating * 10).toInt()
        movie_genre.text = movie.Genre
        year_detail.text = movie.Year
        desc_detail.text = movie.Plot
    }
}