package com.jedischool.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MovieListAdapter.MovieClicked {

    private val movies: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addMovies()

        val mList: RecyclerView = findViewById(R.id.movieRecycler)
        mList.layoutManager = LinearLayoutManager(this)
        mList.adapter = MovieListAdapter(movies, this,this)
    }

    override fun onMovieClicked(pos: Int) {
        val intent = Intent(this, MovieDetail::class.java)
        intent.putExtra("movie_title",movies[pos].title)
        intent.putExtra("movie_year",movies[pos].year.toString())
        intent.putExtra("movie_desc",movies[pos].description)
        intent.putExtra("movie_poster",movies[pos].poster)
        intent.putExtra("movie_bg",movies[pos].background)
        startActivity(intent)
    }

    private fun addMovies() {
        movies.add(Movie("Kirik Party","Karna, a first-year engineering student, falls in love with Saanvi, a final-year pupil from his college.",
            2016,"https://upload.wikimedia.org/wikipedia/en/1/1f/Kirik_Part_Poster.jpg","https://static.toiimg.com/thumb/msid-57162961,width-800,height-600,resizemode-75,imgsize-52937,pt-32,y_pad-40/57162961.jpg"))
        movies.add(Movie("Love Mocktail","After rescuing a woman, Adi reminisces about his love life and tells her about all the heartbreaks he endured in his quest to find his true love.",
            2020,"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSEZIl4nZBgJbtNtdE4b4Drq-LnBBj9Ac8FIgzSZ7xoZZ2KDayT","https://upload.wikimedia.org/wikipedia/en/0/0c/Love_Mocktail_poster.jpg"))
        movies.add(Movie("Mayabazar","An honest policeman, a con man, and a hopeless romantic form an unusual team during the days of demonetization to pull off a scam that quickly goes haywire.",
            2016,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSBXFujYTHPZqGCmL4zo5m_mNTOHWNhSPT8q7oozOzdNDzyfvMm","https://i.ytimg.com/vi/kRPmn3MLToU/maxresdefault.jpg"))
        movies.add(Movie("Ugramm","When Agastya visits Mughor, he witnesses girls being raped in front of the villagers. He fights against the thugs to stop this but becomes a target of the local mafia.",
            2014,"https://www.filmibeat.com/ph-big/2013/09/kannada-movie-ugramm_137915622020.jpg","https://static.toiimg.com/photo/msid-30868483/30868483.jpg?77260"))
        movies.add(Movie("K.G.F: Chapter 1","Rocky, a young man, seeks power and wealth in order to fulfil a promise to his dying mother.",
            2018,"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQDUBv_iHQkErMkhfMKpklqUN5bo01oetTAvEo8RbZ2BJ-s6O1x","https://1847884116.rsc.cdn77.org/tamil/gallery/movies/kgf/main.jpg"))
        movies.add(Movie("Kavaludaari","Shyam, a traffic constable, seeks the help of Muthanna, a retired police officer, to solve the mysterious case of three skulls found near a metro construction site.",
            2019,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWoT27MVHIv4wQHsNyu-LnhFRoMgDrHWHFf6SsmgHtz86gXsZN","https://i.ytimg.com/vi/5w1vgMoPMRA/maxresdefault.jpg"))
        movies.add(Movie("Operation Alamelamma","Purmy is a vegetable vendor who is fascinated with brands. However, he gets framed for kidnapping John, the schoolgoing son of a businessman, after he picks up an abandoned luggage bag found on road.",
            2017,"https://upload.wikimedia.org/wikipedia/en/e/e4/Operation_Alamelamma_poster.jpg","https://i.ytimg.com/vi/_5CcksSThA4/maxresdefault.jpg"))
        movies.add(Movie("Kotigobba","A humble autorickshaw driver leads a happy and contented life with his ambitious siblings. However, his siblings are unaware of their brother's past.",
            2001,"https://images-na.ssl-images-amazon.com/images/I/41p8uwPAF7L.jpg","https://i.ytimg.com/vi/woFGPHHr6Kw/maxresdefault.jpg"))
    }
}