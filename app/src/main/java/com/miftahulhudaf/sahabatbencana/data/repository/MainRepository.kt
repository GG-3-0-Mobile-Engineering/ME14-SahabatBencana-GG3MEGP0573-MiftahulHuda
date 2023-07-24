package com.miftahulhudaf.sahabatbencana.data.repository

import com.miftahulhudaf.sahabatbencana.data.api.ApiHelper
import com.miftahulhudaf.sahabatbencana.data.api.ApiService
import com.miftahulhudaf.sahabatbencana.data.response.APIResponse
import com.miftahulhudaf.sahabatbencana.utils.NetworkState

class MainRepository(private val apiService: ApiService) {
    suspend fun getDisasters() : NetworkState<APIResponse> {
        val response = apiService.getDisasters()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if ((responseBody != null) && (responseBody.statusCode == 200)) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }
}