package com.miftahulhudaf.sahabatbencana.data.response.archive

import com.google.gson.annotations.SerializedName

data class Disaster(

    @field:SerializedName("geometry")
    val geometry: Geometry,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("properties")
    val properties: DisasterProperty
)
