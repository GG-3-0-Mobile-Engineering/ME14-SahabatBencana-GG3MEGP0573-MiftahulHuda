package com.miftahulhudaf.sahabatbencana.data.response.archive

import com.google.gson.annotations.SerializedName

data class Disaster(

    @field:SerializedName("geometry")
    val geometry: Geometry? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("properties")
    val properties: DisasterProperty? = null
)
