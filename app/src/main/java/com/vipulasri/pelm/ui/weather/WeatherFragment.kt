package com.vipulasri.pelm.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.vipulasri.pelm.R
import com.vipulasri.pelm.databinding.FragmentWeatherBinding
import com.vipulasri.pelm.domain.model.WeatherDetails
import com.vipulasri.pelm.extensions.showOrHide
import com.vipulasri.pelm.ui.base.BaseFragment
import com.vipulasri.pelm.ui.weather.cities.CitiesBottomSheet
import com.vipulasri.pelm.utils.nonNull
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Vipul Asri on 29/01/22.
 */

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding
        get() = FragmentWeatherBinding::inflate

    private val viewModel: WeatherVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() {
        binding.textCity.setOnClickListener {
            CitiesBottomSheet.show(childFragmentManager)
        }
    }

    private fun setupObservers() {
        viewModel.selectedCity.observe(viewLifecycleOwner) { city ->
            if (city != null) {
                binding.viewError.showOrHide(false)
                binding.textCity.run {
                    isVisible = true
                    text = city.name
                }
            } else {
                binding.textCity.isVisible = false
                binding.viewError.showOrHide(
                    true,
                    getString(R.string.error_no_cities)
                )
            }
        }

        viewModel.viewState.nonNull().observe(viewLifecycleOwner) { state ->
            when (state) {
                is WeatherViewState.Loading -> {
                    binding.groupWeatherDetails.isVisible = false
                    binding.progress.isVisible = true
                    binding.viewError.showOrHide(false)
                }
                is WeatherViewState.Details -> {
                    binding.progress.isVisible = false
                    binding.groupWeatherDetails.isVisible = true
                    binding.viewError.showOrHide(false)
                    showDetails(state.data)
                }
                is WeatherViewState.Error -> {
                    binding.progress.isVisible = false
                    binding.groupWeatherDetails.isVisible = false
                    binding.viewError.showOrHide(
                        true,
                        getString(R.string.error_fetching_weather),
                        onRetry = {
                            viewModel.loadWeatherDetails()
                        }
                    )
                }
            }
        }
    }

    private fun showDetails(weather: WeatherDetails) {
        binding.run {
            textTemperature.text = String.format(
                getString(R.string.temperature_with_unit),
                weather.temperature,
                weather.temperatureUnit
            )

            val feelsLikeTemperature = String.format(
                getString(R.string.temperature_with_unit),
                weather.feelsLike,
                weather.temperatureUnit
            )
            textFeelsLike.text = "${getString(R.string.feels_like)} $feelsLikeTemperature"

            textCondition.text = "${getString(R.string.condition)} ${weather.condition}"
            textUpdatedAt.text = "${getString(R.string.updated_at)} ${weather.updatedTime}"
        }
    }
}