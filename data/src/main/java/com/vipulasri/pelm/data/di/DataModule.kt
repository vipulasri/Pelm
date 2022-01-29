package com.vipulasri.pelm.data.di

import com.vipulasri.pelm.data.local.CityLocalDataSource
import com.vipulasri.pelm.data.local.CityLocalDataSourceImpl
import com.vipulasri.pelm.data.remote.WeatherApiService
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSourceImpl
import com.vipulasri.pelm.data.repository.WeatherRepositoryImpl
import com.vipulasri.pelm.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

/**
 * Created by Vipul Asri on 29/01/22.
 */

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object DataModule {

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
    fun provideWeatherRepository(
        remoteDataSource: WeatherRemoteDataSource,
        localDataSource: CityLocalDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            remoteDataSource, localDataSource
        )
    }

}