package com.miftahulhudaf.sahabatbencana.data.model

import com.google.gson.annotations.SerializedName

data class Result(

    @field:SerializedName("features")
    val features: List<Disaster?>? = null,

    @field:SerializedName("type")
    val type: String? = null
)
