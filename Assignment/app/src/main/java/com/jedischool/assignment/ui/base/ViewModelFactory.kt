package com.jedischool.assignment.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jedischool.assignment.data.api.ApiHelper
import com.jedischool.assignment.data.repository.MainRepository
import com.jedischool.assignment.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(MainRepository(apiHelper)
        ) as T
        throw IllegalArgumentException("Unknown Class Name!")
    }
}