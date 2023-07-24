package com.miftahulhudaf.sahabatbencana.data.response.archive

import com.google.gson.annotations.SerializedName

data class Geometry(

    @field:SerializedName("coordinates")
    val coordinates: List<Double?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)