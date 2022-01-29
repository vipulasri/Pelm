package com.vipulasri.pelm.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import com.vipulasri.pelm.databinding.FragmentWeatherBinding
import com.vipulasri.pelm.ui.base.BaseFragment

/**
 * Created by Vipul Asri on 29/01/22.
 */

class WeatherFragment : BaseFragment<FragmentWeatherBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWeatherBinding
        get() = FragmentWeatherBinding::inflate
}