package com.vipulasri.pelm.data.remote

import com.vipulasri.pelm.data.remote.model.WeatherDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Vipul Asri on 29/01/22.
 */

interface WeatherApiService {

    @GET("obsdata/{city_code}/c")
    suspend fun getWeatherDetailsInCelsius(
        @Path("city_code") cityCode: String
    ): WeatherDetailsResponse

    @GET("obsdata/{city_code}/f")
    suspend fun getWeatherDetailsInFahrenheit(
        @Path("city_code") cityCode: String
    ): WeatherDetailsResponse

}