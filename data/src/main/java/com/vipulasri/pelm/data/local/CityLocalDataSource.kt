package com.vipulasri.pelm.data.local

import com.vipulasri.pelm.domain.model.City

/**
 * Created by Vipul Asri on 29/01/22.
 */

interface CityLocalDataSource {
    fun getCities(): List<City>
}