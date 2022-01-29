package com.vipulasri.pelm.data.remote.datasource

import com.vipulasri.pelm.data.mapper.toWeatherDetails
import com.vipulasri.pelm.data.remote.WeatherApiService
import com.vipulasri.pelm.data.remote.model.WeatherDetailsResponse
import com.vipulasri.pelm.data.remote.safeApiCall
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.WeatherDetails
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Vipul Asri on 29/01/22.
 */

class WeatherRemoteDataSourceImpl(
    private val dispatcher: CoroutineDispatcher,
    private val apiService: WeatherApiService
) : WeatherRemoteDataSource {

    override suspend fun getWeatherDetailsInCelsius(cityCode: String): SafeResult<WeatherDetails> {
        return safeApiCall(dispatcher) {
            apiService.getWeatherDetailsInCelsius(cityCode)
        }.prepareWeatherDetails()
    }

    override suspend fun getWeatherDetailsInFahrenheit(cityCode: String): SafeResult<WeatherDetails> {
        return safeApiCall(dispatcher) {
            apiService.getWeatherDetailsInFahrenheit(cityCode)
        }.prepareWeatherDetails()
    }

    private fun SafeResult<WeatherDetailsResponse>.prepareWeatherDetails(): SafeResult<WeatherDetails> {
        return when (this) {
            is SafeResult.Success -> {
                SafeResult.Success(data.toWeatherDetails())
            }
            is SafeResult.Failure -> this
        }
    }

}