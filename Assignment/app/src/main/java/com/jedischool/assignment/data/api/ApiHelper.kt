package com.jedischool.assignment.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun searchMovies(s:String) = apiService.searchMovies("cd407587",s)
    suspend fun getMovie(i:String) = apiService.getMovie("cd407587",i,"full")
}