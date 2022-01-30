package com.vipulasri.pelm.data.remote

import com.vipulasri.pelm.data.WeatherFakeDataHelper
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSourceImpl
import com.vipulasri.pelm.domain.SafeResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Test

/**
 * Created by Vipul Asri on 30/01/22.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRemoteDataSourceTest {


    private val dispatcher = UnconfinedTestDispatcher()
    private val weatherApiService: WeatherApiService = mockk()
    private lateinit var remoteDataSource: WeatherRemoteDataSource
    private val fakeDataHelper = WeatherFakeDataHelper()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        remoteDataSource = WeatherRemoteDataSourceImpl(dispatcher, weatherApiService)
    }

    @Test
    fun `check for weather details in celsius`() = runBlocking {

        val cityCode = fakeDataHelper.cityCode

        coEvery {
            weatherApiService.getWeatherDetailsInCelsius(cityCode)
        } returns fakeDataHelper.getWeatherDetailsResponseInCelsius()

        val result = remoteDataSource.getWeatherDetailsInCelsius(cityCode)

        assert(result is SafeResult.Success)
        assert((result as SafeResult.Success).data.temperatureUnit.toLowerCase() == "c")
    }

    @Test
    fun `check for weather details in fahrenheit`() = runBlocking {

        val cityCode = fakeDataHelper.cityCode

        coEvery {
            weatherApiService.getWeatherDetailsInFahrenheit(cityCode)
        } returns fakeDataHelper.getWeatherDetailsResponseInFahrenheit()

        val result = remoteDataSource.getWeatherDetailsInFahrenheit(cityCode)

        assert(result is SafeResult.Success)
        assert((result as SafeResult.Success).data.temperatureUnit.toLowerCase() == "f")
    }

}