package com.miftahulhudaf.sahabatbencana.data.response.monitoring

import com.google.gson.annotations.SerializedName

data class Properties(

    @field:SerializedName("gaugenameid")
	val gaugeNameId: String,

    @field:SerializedName("observations")
	val observations: List<ObservationsItem>,

    @field:SerializedName("gaugeid")
	val gaugeId: String
)