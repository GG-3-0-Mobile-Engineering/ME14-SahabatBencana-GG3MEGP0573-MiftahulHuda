package com.miftahulhudaf.sahabatbencana.data.api

import com.google.gson.annotations.SerializedName

data class FloodResponse (
    @SerializedName("statusCode" ) var statusCode : Int?    = null,
    @SerializedName("result"     ) var result     : Result? = Result()
)

data class ReportData (
    @SerializedName("report_type"          ) var reportType           : String? = null,
    @SerializedName("accessabilityFailure" ) var accessabilityFailure : Int?    = null,
    @SerializedName("condition"            ) var condition            : Int?    = null
)

data class Tags (
    @SerializedName("district_id"          ) var districtId         : String? = null,
    @SerializedName("region_code"          ) var regionCode         : String? = null,
    @SerializedName("local_area_id"        ) var localAreaId        : String? = null,
    @SerializedName("instance_region_code" ) var instanceRegionCode : String? = null
)

data class Properties (
    @SerializedName("pkey"          ) var pkey         : String?     = null,
    @SerializedName("created_at"    ) var createdAt    : String?     = null,
    @SerializedName("source"        ) var source       : String?     = null,
    @SerializedName("status"        ) var status       : String?     = null,
    @SerializedName("url"           ) var url          : String?     = null,
    @SerializedName("image_url"     ) var imageUrl     : String?     = null,
    @SerializedName("disaster_type" ) var disasterType : String?     = null,
    @SerializedName("report_data"   ) var reportData   : ReportData? = ReportData(),
    @SerializedName("tags"          ) var tags         : Tags?       = Tags(),
    @SerializedName("title"         ) var title        : String?     = null,
    @SerializedName("text"          ) var text         : String?     = null,
    @SerializedName("partner_code"  ) var partnerCode  : String?     = null,
    @SerializedName("partner_icon"  ) var partnerIcon  : String?     = null
)

data class Geometries (
    @SerializedName("type"        ) var type        : String?           = null,
    @SerializedName("properties"  ) var properties  : Properties?       = Properties(),
    @SerializedName("coordinates" ) var coordinates : ArrayList<Double> = arrayListOf()
)

data class Output (
    @SerializedName("type"       ) var type       : String?               = null,
    @SerializedName("geometries" ) var geometries : ArrayList<Geometries> = arrayListOf()
)

data class Objects (
    @SerializedName("output" ) var output : Output? = Output()
)

data class Result (
    @SerializedName("type"    ) var type    : String?           = null,
    @SerializedName("objects" ) var objects : Objects?          = Objects(),
    @SerializedName("arcs"    ) var arcs    : ArrayList<String> = arrayListOf(),
    @SerializedName("bbox"    ) var bbox    : ArrayList<Double> = arrayListOf()
)