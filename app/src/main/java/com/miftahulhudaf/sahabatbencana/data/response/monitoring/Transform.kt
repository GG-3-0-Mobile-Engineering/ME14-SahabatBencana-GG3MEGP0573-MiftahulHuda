package com.miftahulhudaf.sahabatbencana.data.response.monitoring

import com.google.gson.annotations.SerializedName

data class Transform(

	@field:SerializedName("scale")
	val scale: List<Any>,

	@field:SerializedName("translate")
	val translate: List<Any>
)