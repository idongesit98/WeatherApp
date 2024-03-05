package com.zseni.weatherapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//TODO: Suspend functions were removed
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherData(weatherEntity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForeCastsData(forecastEntity: ForecastEntity)

    @Query("DELETE FROM weather_data")
    suspend fun deleteWeatherData()

    @Query("DELETE FROM weather_data")
    suspend fun deleteForeCastData()

    @Query("select * from weather_data")
     suspend fun getWeatherData(): WeatherEntity?

    @Query("select * from weather_forecasts")
    suspend fun getForeCastData(): ForecastEntity?
}