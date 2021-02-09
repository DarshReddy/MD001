package com.jedischool.assignment.data.api

import com.jedischool.assignment.data.model.MovieDetails
import com.jedischool.assignment.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(@Query("apikey") k: String, @Query("s") s: String): SearchResponse
    @GET("/")
    suspend fun getMovie(@Query("apikey") k: String, @Query("i") i: String, @Query("plot") p:String): MovieDetails
}