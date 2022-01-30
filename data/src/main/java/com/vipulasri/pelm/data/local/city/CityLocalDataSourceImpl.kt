package com.vipulasri.pelm.data.local.city

import com.vipulasri.pelm.domain.model.City

/**
 * Created by Vipul Asri on 29/01/22.
 */

class CityLocalDataSourceImpl : CityLocalDataSource {

    private val cities = listOf(
        City("Toronto", "CAON0696"),
        City("Montreal", "CAON0423"),
        City("Ottawa", "CAON0512"),
        City("Vancouver", "CABC0308"),
        City("Calgary", "CAAB0049")
    )

    override fun getCities(): List<City> {
        return cities
    }

}