package com.miftahulhudaf.sahabatbencana.data.response.archive

import com.google.gson.annotations.SerializedName

data class ArchiveResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)
