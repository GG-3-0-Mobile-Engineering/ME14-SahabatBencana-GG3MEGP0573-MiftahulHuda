package com.miftahulhudaf.sahabatbencana.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miftahulhudaf.sahabatbencana.data.api.ApiService
import com.miftahulhudaf.sahabatbencana.data.repository.MainRepository
import com.miftahulhudaf.sahabatbencana.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}