package com.miftahulhudaf.sahabatbencana.data.api

import com.miftahulhudaf.sahabatbencana.data.response.archive.ArchiveResponse
import com.miftahulhudaf.sahabatbencana.data.response.monitoring.MonitoringResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reports/archive?start=2017-12-04T00%3A00%3A00%2B0700&end=2017-12-06T05%3A00%3A00%2B0700&geoformat=geojson")
    suspend fun getDisasters(): Response<ArchiveResponse>

    @GET("reports/archive")
    suspend fun getReportsArchive(
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("geoformat") geoFormat: String = "geojson",
        @Query("admin") city: String? = null,
    ): ArchiveResponse

    @GET("floodgauges")
    suspend fun getMonitoring(
        @Query("admin") admin: String = "ID-JK",
    ): MonitoringResponse
}