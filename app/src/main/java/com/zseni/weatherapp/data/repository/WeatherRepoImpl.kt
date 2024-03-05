package com.zseni.weatherapp.data.repository

import android.util.Log
import com.zseni.weatherapp.data.api.RemoteDataSource
import com.zseni.weatherapp.data.api.remote.currentweather.CurrentWeatherDto
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDto
import com.zseni.weatherapp.data.local.mappers.toEntity
import com.zseni.weatherapp.data.local.mappers.toForeCastData
import com.zseni.weatherapp.data.local.mappers.toWeatherData
import com.zseni.weatherapp.data.local.room.ForecastEntity
import com.zseni.weatherapp.data.local.room.LocalDataSource
import com.zseni.weatherapp.data.local.room.WeatherEntity
import com.zseni.weatherapp.domain.model.ForecastData
import com.zseni.weatherapp.domain.model.WeatherData
import com.zseni.weatherapp.domain.repository.WeatherRepository
import com.zseni.weatherapp.util.AppComponents.EXPIRED_TIME
import com.zseni.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
):WeatherRepository{
    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Flow<Resource<WeatherData>> = flow {
        fetchWeatherFromLocal()?.takeIf { !it.isExpired() }?.let {
            emit(Resource.Success(it.toWeatherData()))
        }?: run {
            when (val result = remoteDataSource.getWeather(latitude, longitude)){
                is Resource.Success ->{
                    localDataSource.deleteWeather()
                    result.data?.let {
                        insertWeatherResponse(it)
                    }
                    val localResult = fetchWeatherFromLocal()?.toWeatherData()
                    emit(Resource.Success(localResult))
                }
                is Resource.Error ->{
                    val localResult = fetchWeatherFromLocal()?.toWeatherData()
                    Resource.Error(localResult, result.message)
                }

                else -> {
                    emit(Resource.Loading())
                }
            }
        }
    }

    override suspend fun getForeCastData(
        latitude: Double,
        longitude: Double,
    ): Flow<Resource<ForecastData>> =
        flow {
            fetchWeatherForeCastFromLocal()?.takeIf {!it.isExpired() }?.let {
                emit(Resource.Success(it.toForeCastData())) }
                ?: run {
                    when(val result =
                        remoteDataSource.getForeCast(latitude, longitude)){
                        is Resource.Success -> {
                            Log.d("WeatherRepository", "Remote data received successfully:${result.data}")
                            localDataSource.deleteForeCast()
                            result.data?.let {
                                insertForecastsResponse(it)
                            }

                            val localResult = fetchWeatherForeCastFromLocal()?.toForeCastData()
                            emit(Resource.Success(localResult))
                        }

                        is Resource.Error -> {
                            val localResult = fetchWeatherForeCastFromLocal()?.toForeCastData()
                            Resource.Error(localResult, result.message)
                        }

                        else ->{
                            emit(Resource.Loading())
                        }
                    }
                }

        }


    private suspend fun fetchWeatherFromLocal(): WeatherEntity? =
        localDataSource.getWeather()

    private suspend fun fetchWeatherForeCastFromLocal(): ForecastEntity? =
        localDataSource.getForeCasts()

    private fun WeatherEntity.isExpired():Boolean =
        System.currentTimeMillis() - dt > EXPIRED_TIME

    private fun ForecastEntity.isExpired():Boolean =
        System.currentTimeMillis() - cnt > EXPIRED_TIME

    private suspend fun insertWeatherResponse(remoteData: CurrentWeatherDto){
        localDataSource.insertWeatherData(
            remoteData.toEntity(
                lastFetchedTime = System.currentTimeMillis()
            )
        )
    }

    private suspend fun insertForecastsResponse(remoteData:ForeCastDto){
        localDataSource.insertForecastData(
            remoteData.toEntity(
                lastFetchedTime = System.currentTimeMillis()
            )
        )
    }


}



