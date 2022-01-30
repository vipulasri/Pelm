package com.vipulasri.pelm.data

import com.vipulasri.pelm.data.mapper.toWeatherDetails
import com.vipulasri.pelm.data.remote.model.WeatherDetailsResponse
import com.vipulasri.pelm.domain.model.City

/**
 * Created by Vipul Asri on 30/01/22.
 */

class WeatherFakeDataHelper {

    val cityCode = "2"

    fun getCities() = listOf(
        City("New York", "1"),
        City("Boston", "2")
    )

    fun getWeatherDetailsResponseInCelsius() = WeatherDetailsResponse(
        feelsLike = -15,
        temperature = -10,
        temperatureUnit = "C",
        updateTime = "Sun Jan 30 7:35 AM",
        wxCondition = "Fair",
        placeCode = cityCode,
        lblUpdateTime = "Updated on:",
        icon = "",
        inic = "",
        updateTimeStampGmt = "8467482920"
    )

    fun getWeatherDetailsResponseInFahrenheit() = WeatherDetailsResponse(
        feelsLike = 9,
        temperature = 19,
        temperatureUnit = "F",
        updateTime = "Sun Jan 30 7:35 AM",
        wxCondition = "Fair",
        placeCode = cityCode,
        lblUpdateTime = "Updated on:",
        icon = "",
        inic = "",
        updateTimeStampGmt = "8467482920"
    )

    fun getWeatherDetailsInCelsius() = getWeatherDetailsResponseInCelsius().toWeatherDetails()

    fun getWeatherDetailsInFahrenheit() = getWeatherDetailsResponseInFahrenheit().toWeatherDetails()

}