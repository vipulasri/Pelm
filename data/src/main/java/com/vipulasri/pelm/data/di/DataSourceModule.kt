package com.vipulasri.pelm.data.di

import com.vipulasri.pelm.data.local.city.CityLocalDataSource
import com.vipulasri.pelm.data.local.city.CityLocalDataSourceImpl
import com.vipulasri.pelm.data.local.photo.PhotoLocalDataSource
import com.vipulasri.pelm.data.local.photo.PhotoLocalDataSourceImpl
import com.vipulasri.pelm.data.remote.WeatherApiService
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

/**
 * Created by Vipul Asri on 30/01/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideWeatherRemoteDataSource(apiService: WeatherApiService): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(
            Dispatchers.IO,
            apiService
        )
    }

    @Provides
    fun provideCityLocalDataSource(): CityLocalDataSource {
        return CityLocalDataSourceImpl()
    }

    @Provides
    fun providePhotoLocalDataSource(): PhotoLocalDataSource {
        return PhotoLocalDataSourceImpl()
    }

}