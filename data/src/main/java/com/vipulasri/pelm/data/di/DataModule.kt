package com.vipulasri.pelm.data.di

import com.vipulasri.pelm.data.local.city.CityLocalDataSource
import com.vipulasri.pelm.data.local.photo.PhotoLocalDataSource
import com.vipulasri.pelm.data.remote.datasource.WeatherRemoteDataSource
import com.vipulasri.pelm.data.repository.PhotoRepositoryImpl
import com.vipulasri.pelm.data.repository.WeatherRepositoryImpl
import com.vipulasri.pelm.domain.repository.PhotoRepository
import com.vipulasri.pelm.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Vipul Asri on 29/01/22.
 */

@Module(
    includes = [
        NetworkModule::class,
        DataSourceModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideWeatherRepository(
        remoteDataSource: WeatherRemoteDataSource,
        localDataSource: CityLocalDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            remoteDataSource, localDataSource
        )
    }

    @Provides
    fun providePhotoRepository(
        localDataSource: PhotoLocalDataSource
    ): PhotoRepository {
        return PhotoRepositoryImpl(
            localDataSource
        )
    }

}