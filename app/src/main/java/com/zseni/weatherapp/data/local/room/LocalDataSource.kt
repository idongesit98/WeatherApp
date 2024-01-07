package com.zseni.weatherapp.data.local.room

import com.zseni.weatherapp.data.local.mappers.WeatherEntityMapper
import com.zseni.weatherapp.domain.model.EmptyWeatherData
import com.zseni.weatherapp.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val weatherDao: WeatherDao,
) {
     fun insertWeatherData(weatherData: WeatherData) {
       val weatherEntity = WeatherEntityMapper.mapWeatherDataToWeatherEntity(weatherData)
        weatherDao.insertWeatherData(weatherEntity)

    }
     fun getWeatherData(): Flow<WeatherData> =
         weatherDao.getWeatherData().map { weatherEntity ->
             weatherEntity?.let {
                 WeatherEntityMapper.mapWeatherEntityToWeatherData(it)
             }?:EmptyWeatherData.instance
         } // check the meaning of this

//        return weatherDao.getWeatherData().map {weatherEntity ->
//            WeatherEntityMapper.mapWeatherEntityToWeatherData(weatherEntity)
//        }

    fun deleteWeatherData(){
        weatherDao.deleteWeatherData()
    }
}