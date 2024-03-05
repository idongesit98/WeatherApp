package com.zseni.weatherapp.data.local.room

import com.zseni.weatherapp.di.IoDispatcher
import com.zseni.weatherapp.domain.model.WeatherData
import com.zseni.weatherapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface LocalDataSource{
    suspend fun insertWeatherData(weatherEntity: WeatherEntity)

    suspend fun insertForecastData(weatherForecastEntity: ForecastEntity)

    suspend fun getForeCasts():ForecastEntity?

    suspend fun getWeather():WeatherEntity?

    suspend fun deleteWeather()

    suspend fun deleteForeCast()
}
class LocalDataSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    @IoDispatcher private val ioDisPatcher:CoroutineDispatcher
):LocalDataSource {

    override suspend fun insertWeatherData(weatherEntity: WeatherEntity) {
        withContext(ioDisPatcher){
            weatherDao.insertWeatherData(weatherEntity = weatherEntity)
        }
    }

    override suspend fun insertForecastData(weatherForecastEntity: ForecastEntity) {
        withContext(ioDisPatcher){
            weatherDao.insertForeCastsData(forecastEntity = weatherForecastEntity)
        }
    }

    override suspend fun getForeCasts(): ForecastEntity? =
       withContext(ioDisPatcher){
           weatherDao.getForeCastData()
       }


    override suspend fun getWeather(): WeatherEntity? =
        withContext(ioDisPatcher){
            weatherDao.getWeatherData()
        }

    override suspend fun deleteWeather() {
        withContext(ioDisPatcher){
            weatherDao.deleteWeatherData()
        }
    }

    override suspend fun deleteForeCast() {
        withContext(ioDisPatcher){
            weatherDao.deleteForeCastData()
        }
    }
}