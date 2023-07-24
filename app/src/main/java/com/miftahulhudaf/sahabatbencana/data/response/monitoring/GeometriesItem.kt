package com.miftahulhudaf.sahabatbencana.data.response.monitoring

import com.google.gson.annotations.SerializedName

data class GeometriesItem(

	@field:SerializedName("coordinates")
	val coordinates: List<Int>,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("properties")
	val properties: Properties
)