package com.jedischool.assignment

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetail: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val title = intent.getStringExtra("movie_title")
        val year  = intent.getStringExtra("movie_year")
        val desc  = intent.getStringExtra("movie_desc")
        val poster= intent.getStringExtra("movie_poster")
        val background = intent.getStringExtra("movie_bg")

        val mPoster : ImageView = findViewById(R.id.movie_poster_detail)
        val mBackground : ImageView = findViewById(R.id.movie_background)
        val mTitle : TextView = findViewById(R.id.title_detail)
        val mYear: TextView = findViewById(R.id.year_detail)
        val mDesc: TextView = findViewById(R.id.desc_detail)
        val mBtn: Button = findViewById(R.id.play_trailer)

        Glide.with(this).load(poster).into(mPoster)
        Glide.with(this).load(background).into(mBackground)
        mTitle.text = title
        mYear.text = "($year)"
        mDesc.text = desc

        mBtn.setOnClickListener { btnClick() }
    }

    private fun btnClick() {
        Toast.makeText(this,"Playing Trailer",Toast.LENGTH_LONG).show()
    }
}