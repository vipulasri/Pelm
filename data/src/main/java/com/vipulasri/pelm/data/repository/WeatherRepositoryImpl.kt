package com.vipulasri.pelm.data.repository

import com.vipulasri.pelm.data.local.city.CityLocalDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.City
import com.vipulasri.pelm.domain.model.TemperatureUnit
import com.vipulasri.pelm.domain.model.WeatherDetails
import com.vipulasri.pelm.domain.repository.WeatherRepository

/**
 * Created by Vipul Asri on 29/01/22.
 */

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: CityLocalDataSource
) : WeatherRepository {

    override suspend fun getWeatherDetails(
        cityCode: String,
        temperatureUnit: TemperatureUnit
    ): SafeResult<WeatherDetails> {
        return when (temperatureUnit) {
            TemperatureUnit.FAHRENHEIT -> {
                remoteDataSource.getWeatherDetailsInFahrenheit(cityCode)
            }
            else -> {
                remoteDataSource.getWeatherDetailsInCelsius(cityCode)
            }
        }
    }

    override suspend fun getCities(): SafeResult<List<City>> {
        return SafeResult.Success(localDataSource.getCities())
    }

}