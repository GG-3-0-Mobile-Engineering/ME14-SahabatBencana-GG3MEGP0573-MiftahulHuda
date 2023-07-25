package com.miftahulhudaf.sahabatbencana.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.miftahulhudaf.sahabatbencana.data.repository.MainRepository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.miftahulhudaf.sahabatbencana.data.response.archive.Disaster
import com.miftahulhudaf.sahabatbencana.data.utils.Resource
import com.miftahulhudaf.sahabatbencana.utils.NetworkState
import kotlinx.coroutines.*

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val _searchQuery = MutableLiveData<String?>()
    private val _filterTypes = MutableLiveData<List<String>>()

    private val _archiveFeatures: LiveData<Resource<List<Disaster>>> =
        _searchQuery.switchMap { query ->
            repository.getReportsArchive(query).asLiveData()
        }

    val supportedLocations = repository.supportedLocations
    val disasterType = repository.disasterTypes

    init {
        _searchQuery.value = null
        _filterTypes.value = emptyList()
    }

    val filteredArchiveFeatures: LiveData<Resource<List<Disaster>>> =
        _filterTypes.switchMap { filterTypes ->
            if (filterTypes.isEmpty()) {
                _archiveFeatures
            } else {
                filterArchiveFeatures(filterTypes)
            }
        }

    private fun filterArchiveFeatures(types: List<String>): LiveData<Resource<List<Disaster>>> {
        return _archiveFeatures.map { resource ->
            if (resource is Resource.Success) {
                val filteredFeatures = filterFeatures(resource.data!!, types)
                Resource.Success(filteredFeatures)
            } else {
                resource
            }
        }
    }

    fun searchCity(query: String? = null) {
        _searchQuery.value = query
    }

    fun applyFilter(type: String): Boolean {
        val currentFilterTypes = _filterTypes.value ?: emptyList()

        return if (type in currentFilterTypes) {
            val updatedFilterTypes = currentFilterTypes - type
            _filterTypes.postValue(updatedFilterTypes)

            false
        } else {
            val updatedFilterTypes = currentFilterTypes + type
            _filterTypes.postValue(updatedFilterTypes)

            true
        }
    }

    private fun filterFeatures(
        features: List<Disaster>,
        disasterType: List<String>
    ): List<Disaster> {
        return features.filter { it.properties.disasterType in disasterType }
    }

}