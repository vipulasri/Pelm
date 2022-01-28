package com.vipulasri.pelm.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Vipul Asri on 29/01/22.
 */

@JsonClass(generateAdapter = true)
data class WeatherDetailsResponse(
    @Json(name = "feels_like")
    val feelsLike: Int?,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "inic")
    val inic: String?,
    @Json(name = "lbl_updatetime")
    val lblUpdateTime: String?,
    @Json(name = "placecode")
    val placeCode: String?,
    @Json(name = "temperature")
    val temperature: Int?,
    @Json(name = "temperature_unit")
    val temperatureUnit: String?,
    @Json(name = "updatetime")
    val updateTime: String?,
    @Json(name = "updatetime_stamp_gmt")
    val updateTimeStampGmt: String?,
    @Json(name = "wxcondition")
    val wxCondition: String?
)