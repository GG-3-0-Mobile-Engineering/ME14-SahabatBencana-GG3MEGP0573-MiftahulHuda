package com.miftahulhudaf.sahabatbencana.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getDisasters() = apiService.getDisasters()
}