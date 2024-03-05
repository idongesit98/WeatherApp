package com.zseni.weatherapp.di

import com.zseni.weatherapp.data.api.RemoteDataSource
import com.zseni.weatherapp.data.local.room.LocalDataSource
import com.zseni.weatherapp.data.repository.WeatherRepoImpl
import com.zseni.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideWeatherDataRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): WeatherRepository {
        //TODO: I mixed up the parameters
        return WeatherRepoImpl(remoteDataSource, localDataSource)
    }

}