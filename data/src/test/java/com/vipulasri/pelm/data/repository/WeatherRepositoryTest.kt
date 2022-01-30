package com.vipulasri.pelm.data.repository

import com.vipulasri.pelm.data.WeatherFakeDataHelper
import com.vipulasri.pelm.data.local.CityLocalDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.TemperatureUnit
import com.vipulasri.pelm.domain.repository.WeatherRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Vipul Asri on 30/01/22.
 */

class WeatherRepositoryTest {

    private val cityLocalDataSource: CityLocalDataSource = mockk()
    private val weatherRemoteDataSource: WeatherRemoteDataSource = mockk()
    private lateinit var repository: WeatherRepository
    private val fakeDataHelper = WeatherFakeDataHelper()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = WeatherRepositoryImpl(weatherRemoteDataSource, cityLocalDataSource)
    }

    @Test
    fun `check if cities are available`() = runBlocking {

        coEvery { cityLocalDataSource.getCities() } returns fakeDataHelper.getCities()

        val citiesResult = repository.getCities()

        assert(citiesResult is SafeResult.Success)
        assert((citiesResult as SafeResult.Success).data.isNotEmpty())
    }

    @Test
    fun `check for weather details`() = runBlocking {

        val cityCode = fakeDataHelper.cityCode

        coEvery { weatherRemoteDataSource.getWeatherDetailsInCelsius(cityCode) } returns SafeResult.Success(
            fakeDataHelper.getWeatherDetailsInCelsius()
        )

        coEvery { weatherRemoteDataSource.getWeatherDetailsInFahrenheit(cityCode) } returns SafeResult.Success(
            fakeDataHelper.getWeatherDetailsInFahrenheit()
        )

        val weatherDetailsResultCelsius =
            repository.getWeatherDetails(cityCode, TemperatureUnit.CELSIUS)
        val weatherDetailsResultFahrenheit =
            repository.getWeatherDetails(cityCode, TemperatureUnit.FAHRENHEIT)

        assert(weatherDetailsResultCelsius is SafeResult.Success)
        assert((weatherDetailsResultCelsius as SafeResult.Success).data.temperatureUnit.toLowerCase() == "c")

        assert(weatherDetailsResultFahrenheit is SafeResult.Success)
        assert((weatherDetailsResultFahrenheit as SafeResult.Success).data.temperatureUnit.toLowerCase() == "f")
    }

    @Test
    fun `check for internet connection error`() = runBlocking {
        val error = Exception("No Internet Connection!")

        coEvery { weatherRemoteDataSource.getWeatherDetailsInCelsius(any()) } returns SafeResult.Failure(
            error
        )

        val weatherDetailsResult = repository.getWeatherDetails("1", TemperatureUnit.CELSIUS)

        assert(weatherDetailsResult is SafeResult.Failure)
        assert((weatherDetailsResult as SafeResult.Failure).exception == error)
    }
}