package com.zseni.weatherapp.data.repository

import com.zseni.weatherapp.data.api.RemoteDataSource
import com.zseni.weatherapp.data.local.room.LocalDataSource
import com.zseni.weatherapp.domain.model.WeatherData
import com.zseni.weatherapp.domain.repository.WeatherRepository
import com.zseni.weatherapp.util.Resource
import com.zseni.weatherapp.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):WeatherRepository{
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
                }
    ) { weatherData ->
        weatherData.forecasts.isEmpty()
    }
}
