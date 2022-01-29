package com.vipulasri.pelm.data.mapper

import com.vipulasri.pelm.data.remote.model.WeatherDetailsResponse
import com.vipulasri.pelm.domain.model.WeatherDetails

/**
 * Created by Vipul Asri on 29/01/22.
 */

fun WeatherDetailsResponse.toWeatherDetails(): WeatherDetails {
    return WeatherDetails(
        updatedTime = updateTime ?: "",
        condition = wxCondition ?: "",
        temperature = temperature?.toString() ?: "--",
        feelsLike = feelsLike?.toString() ?: "--",
        cityCode = placeCode ?: "",
        temperatureUnit = temperatureUnit ?: ""
    )
}