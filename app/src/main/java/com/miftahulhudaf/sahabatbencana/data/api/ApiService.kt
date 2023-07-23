package com.miftahulhudaf.sahabatbencana.data.api

import com.miftahulhudaf.sahabatbencana.data.response.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("reports/archive?start=2017-12-04T00%3A00%3A00%2B0700&end=2017-12-06T05%3A00%3A00%2B0700&geoformat=geojson")
    suspend fun getDisasters(): Response<APIResponse>
}