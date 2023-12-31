package com.zseni.weatherapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//TODO: Suspend functions were removed
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(weatherEntity: WeatherEntity)

    @Query("DELETE FROM weather_data")
    fun deleteWeatherData()

    @Query("select * from weather_data")
     fun getWeatherData(): Flow<WeatherEntity?>
}