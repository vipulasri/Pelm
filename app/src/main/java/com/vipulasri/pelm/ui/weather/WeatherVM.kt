package com.vipulasri.pelm.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vipulasri.pelm.domain.handleResult
import com.vipulasri.pelm.domain.model.City
import com.vipulasri.pelm.domain.model.TemperatureUnit
import com.vipulasri.pelm.domain.model.WeatherDetails
import com.vipulasri.pelm.domain.repository.WeatherRepository
import com.vipulasri.pelm.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Vipul Asri on 29/01/22.
 */

@HiltViewModel
class WeatherVM @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseVM() {

    private val cities = mutableListOf<City>()

    val selectedCity: LiveData<City> = MutableLiveData()
    val viewState: LiveData<WeatherViewState> = MutableLiveData()

    init {
        loadCities()
    }

    fun getCities(): List<City> = cities

    fun setSelectedCity(city: City?) {
        selectedCity.setValue(city)
        city?.let {
            getWeatherDetails(city)
        }
    }

    fun loadWeatherDetails() {
        selectedCity.value?.let { city ->
            getWeatherDetails(city)
        }
    }

    private fun loadCities() {
        viewModelScope.launch {
            val result = weatherRepository.getCities()
            result.handleResult(
                onSuccess = { cityList ->
                    cities.clear()
                    cities.addAll(cityList)
                    setSelectedCity(cityList.firstOrNull())
                },
                onError = {
                    cities.clear()
                }
            )
        }
    }

    private fun getWeatherDetails(city: City) {
        viewModelScope.launch {
            viewState.setValue(WeatherViewState.Loading)
            val result = weatherRepository.getWeatherDetails(city.code, TemperatureUnit.CELSIUS)
            result.handleResult(
                onSuccess = { details ->
                    viewState.setValue(WeatherViewState.Details(details))
                },
                onError = { error ->
                    viewState.setValue(WeatherViewState.Error(error.message))
                }
            )
        }
    }

}

sealed class WeatherViewState {
    object Loading : WeatherViewState()
    class Details(val data: WeatherDetails) : WeatherViewState()
    class Error(val message: String) : WeatherViewState()
}