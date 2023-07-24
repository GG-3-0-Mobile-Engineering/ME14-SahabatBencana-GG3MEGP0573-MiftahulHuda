package com.miftahulhudaf.sahabatbencana.data.response

import com.google.gson.annotations.SerializedName
import com.miftahulhudaf.sahabatbencana.data.model.Result

data class APIResponse(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)
