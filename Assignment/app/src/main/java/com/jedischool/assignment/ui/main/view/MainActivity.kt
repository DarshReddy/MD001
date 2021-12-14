package com.jedischool.assignment.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jedischool.assignment.ui.main.adapter.MainAdapter
import com.jedischool.assignment.R
import com.jedischool.assignment.data.api.ApiHelper
import com.jedischool.assignment.data.api.RetrofitBuilder
import com.jedischool.assignment.data.model.Movie
import com.jedischool.assignment.data.model.SearchResponse
import com.jedischool.assignment.ui.base.ViewModelFactory
import com.jedischool.assignment.ui.main.viewmodel.MainViewModel
import com.jedischool.assignment.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainAdapter.MovieClicked {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var movies: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_btn.setOnClickListener { searchClicked() }
    }

    private fun searchClicked() {
        if(search_query.text.equals("")) Toast.makeText(this,"Enter search term",Toast.LENGTH_LONG).show()
        else {
            setupViewModel()
            setupUI()
            setupObservers()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        movieRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this,this)
        movieRecycler.addItemDecoration(
            DividerItemDecoration(
                movieRecycler.context,
                (movieRecycler.layoutManager as LinearLayoutManager).orientation
            )
        )
        movieRecycler.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.searchMovies(search_query.text.toString()).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        movieRecycler.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { response -> retrieveList(response) }
                    }
                    Status.ERROR -> {
                        movieRecycler.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        movieRecycler.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(response: SearchResponse)
    {
        this.movies = response.Search
        adapter.apply {
            addMovies(response.Search)
            notifyDataSetChanged()
        }
    }

    override fun onMovieClicked(pos: Int) {
        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("movie_id",movies[pos].imdbID)
        startActivity(intent)
    }
}