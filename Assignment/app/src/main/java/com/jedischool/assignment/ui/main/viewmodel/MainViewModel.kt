package com.jedischool.assignment.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jedischool.assignment.data.repository.MainRepository
import com.jedischool.assignment.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    fun searchMovies(s:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.searchMovies(s)))
        } catch (exception : Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getMovie(i:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getMovie(i)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error!"))
        }
    }
}