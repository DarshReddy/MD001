package com.jedischool.assignment.data.model

data class MovieDetails (

    val Title : String,
    val Year : String,
    val Rated : String,
    val Released : String,
    val Runtime : String,
    val Genre : String,
    val Director : String,
    val Writer : String,
    val Actors : String,
    val Plot : String,
    val language : String,
    val country : String,
    val awards : String,
    val Poster : String,
    val ratings : List<Ratings>,
    val metascore : Int,
    val imdbRating : Double,
    val imdbVotes : String,
    val imdbID : String,
    val type : String,
    val dVD : String,
    val boxOffice : String,
    val production : String,
    val website : String,
    val response : Boolean
)
