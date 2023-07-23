package com.miftahulhudaf.sahabatbencana.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.miftahulhudaf.sahabatbencana.data.repository.MainRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.miftahulhudaf.sahabatbencana.data.response.Disaster
import com.miftahulhudaf.sahabatbencana.utils.NetworkState
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val disasterList = MutableLiveData<List<Disaster>>()
    var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllDisaster() {
        Log.d("Thread Outside", Thread.currentThread().name)

        viewModelScope.launch {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = mainRepository.getDisasters()) {
                is NetworkState.Success -> {
                    Log.d("API", response.data.result?.features.toString())
                    disasterList.postValue(response.data.result?.features as List<Disaster>?)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}