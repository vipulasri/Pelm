package com.vipulasri.pelm.domain.model

/**
 * Created by Vipul Asri on 29/01/22.
 */

data class WeatherDetails(
    val updatedTime: String,
    val condition: String,
    val temperature: String,
    val feelsLike: String,
    val cityCode: String,
    val temperatureUnit: String
)
