package com.vipulasri.pelm.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.City
import com.vipulasri.pelm.domain.model.WeatherDetails
import com.vipulasri.pelm.domain.repository.WeatherRepository
import io.mockk.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Vipul Asri on 30/01/22.
 */

class WeatherVMTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private val repository: WeatherRepository = mockk(relaxed = true)
    private lateinit var viewModel: WeatherVM

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = WeatherVM(repository)
    }

    @Test
    fun NoCitiesAvailable() = runBlocking {
        coEvery { repository.getCities() } returns SafeResult.Success(emptyList())
        assert(viewModel.selectedCity.value == null)
    }

    @Test
    fun CheckForWeatherDetails() = runBlocking {
        val details = WeatherDetails(
            feelsLike = "9",
            temperature = "19",
            temperatureUnit = "F",
            updatedTime = "Sun Jan 30 7:35 AM",
            condition = "Fair",
            cityCode = "2"
        )

        coEvery { repository.getWeatherDetails(any(), any()) } returns SafeResult.Success(details)

        val viewStates = mutableListOf<WeatherViewState>()
        val slot = slot<WeatherViewState>()

        val observer = mockk<Observer<WeatherViewState>>()
        viewModel.viewState.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            println("view state: ${slot.captured}")
            viewStates.add(slot.captured)
        }

        viewModel.loadWeatherDetails(City("NY", "1"))

        delay(2000)

        assert(viewStates[0] is WeatherViewState.Loading)
        assert(viewStates[1] is WeatherViewState.Details)
    }

}