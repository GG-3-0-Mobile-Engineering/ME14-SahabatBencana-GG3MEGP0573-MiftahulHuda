package com.miftahulhudaf.sahabatbencana.data.response.monitoring

import com.google.gson.annotations.SerializedName

data class Result(

    @field:SerializedName("transform")
	val transform: Transform,

    @field:SerializedName("objects")
	val objects: Objects,

    @field:SerializedName("bbox")
	val bbox: List<Any>,

    @field:SerializedName("type")
	val type: String,

    @field:SerializedName("arcs")
	val arcs: List<Any>
)