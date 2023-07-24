package com.miftahulhudaf.sahabatbencana.data.response.monitoring

import com.google.gson.annotations.SerializedName

data class MonitoringResponse(

	@field:SerializedName("result")
	val result: Result,

	@field:SerializedName("statusCode")
	val statusCode: Int
)