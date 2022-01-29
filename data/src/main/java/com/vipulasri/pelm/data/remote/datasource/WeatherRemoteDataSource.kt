package com.vipulasri.pelm.data.remote.datasource

import com.vipulasri.pelm.data.remote.model.WeatherDetailsResponse
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.WeatherDetails

/**
 * Created by Vipul Asri on 29/01/22.
 */

interface WeatherRemoteDataSource {

    suspend fun getWeatherDetailsInCelsius(
        cityCode: String
    ): SafeResult<WeatherDetails>

    suspend fun getWeatherDetailsInFahrenheit(
        cityCode: String
    ): SafeResult<WeatherDetails>
}