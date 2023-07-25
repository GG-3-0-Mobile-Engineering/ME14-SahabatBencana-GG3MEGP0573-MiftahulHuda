package com.miftahulhudaf.sahabatbencana.data.repository

import android.util.Log
import com.miftahulhudaf.sahabatbencana.data.api.ApiService
import com.miftahulhudaf.sahabatbencana.data.model.DisasterType
import com.miftahulhudaf.sahabatbencana.data.model.LocationData
import com.miftahulhudaf.sahabatbencana.data.response.archive.ArchiveResponse
import com.miftahulhudaf.sahabatbencana.data.response.archive.Disaster
import com.miftahulhudaf.sahabatbencana.data.response.monitoring.GeometriesItem
import com.miftahulhudaf.sahabatbencana.data.utils.DateHelper
import com.miftahulhudaf.sahabatbencana.data.utils.LocalData
import com.miftahulhudaf.sahabatbencana.utils.NetworkState
import com.miftahulhudaf.sahabatbencana.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MainRepository(private val apiService: ApiService) {

    val supportedLocations: List<LocationData> = LocalData.getSupportedLocation()
    val disasterTypes: List<DisasterType> = LocalData.getDisasterTypes()

    suspend fun getDisasters() : NetworkState<ArchiveResponse> {
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

    fun getReportsArchive(cityName: String? = null): Flow<Resource<List<Disaster>>> = flow {
        emit(Resource.Loading())

        if (cityName != null) {
            val isContains =
                supportedLocations.any { it.province.lowercase() == cityName.lowercase() }

            if (!isContains) {
                emit(Resource.Error("Can't find $cityName"))
                return@flow
            }
        }

        try {
            val startDate = DateHelper.getDateTimeOneWeekAgoWithTimeZoneOffset()
            val endDate = DateHelper.getCurrentDateTimeWithTimeZoneOffset()

            val cityCode =
                supportedLocations.find { it.province.lowercase() == cityName?.lowercase() }?.code

            val response = apiService.getReportsArchive(startDate, endDate, city = cityCode)
            val features = response.result.features

            if (features.isNotEmpty()) {
                emit(Resource.Success(features))
            } else {
                emit(Resource.Error("Bencana Tidak Ditemukan!"))
            }
        } catch (e: HttpException) {
            Log.e(TAG, "getReportsArchive: ${e.message()}")
            emit(Resource.Error(e.message()))
        }
    }

    fun getMonitoring(): Flow<Resource<List<GeometriesItem>>> = flow {
        try {
            val response = apiService.getMonitoring()
            val geometries = response.result.objects.output.geometries

            if (geometries.isNotEmpty()) {
                emit(Resource.Success(geometries))
            } else {
                emit(Resource.Error("Bencana Tidak Ditemukan!"))
            }
        } catch (e: HttpException) {
            Log.e(TAG, "getMonitoring: ${e.message()}")
            emit(Resource.Error(e.message()))
        }
    }

    private companion object {
        const val TAG = "DisasterRepository"
    }
}