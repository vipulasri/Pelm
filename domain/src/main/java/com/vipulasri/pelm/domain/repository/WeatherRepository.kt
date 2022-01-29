package com.vipulasri.pelm.domain.repository

import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.City
import com.vipulasri.pelm.domain.model.TemperatureUnit
import com.vipulasri.pelm.domain.model.WeatherDetails

/**
 * Created by Vipul Asri on 29/01/22.
 */

interface WeatherRepository {

    suspend fun getWeatherDetails(
        cityCode: String,
        temperatureUnit: TemperatureUnit
    ): SafeResult<WeatherDetails>

    suspend fun getCities(): SafeResult<List<City>>

}