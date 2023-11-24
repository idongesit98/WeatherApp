package com.zseni.weatherapp.data.repository

import com.zseni.weatherapp.data.api.RemoteDataSource
import com.zseni.weatherapp.data.local.room.LocalDataSource
import com.zseni.weatherapp.domain.repository.WeatherRepository
import com.zseni.weatherapp.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):WeatherRepository{

    //TODO: I configured the method properly
    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ) = networkBoundResource(
        query = {
            localDataSource.getWeatherData()
        },
        fetch = {
            delay(2000)
            remoteDataSource.getWeatherData(latitude, longitude).first()
        },
        saveFetchResult = { weatherData ->
            weatherData.data?.let {
                localDataSource.insertWeatherData(it)
                localDataSource.deleteWeatherData()
            }
        },
        shouldFetch = { weatherData ->
            weatherData.forecasts.isEmpty()
        }


    )

}
