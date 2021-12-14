package com.jedischool.assignment.data.repository

import com.jedischool.assignment.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun searchMovies(s:String) = apiHelper.searchMovies(s)
    suspend fun getMovie(i:String) = apiHelper.getMovie(i)
}