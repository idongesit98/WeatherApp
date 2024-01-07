package com.zseni.weatherapp.data.local.room

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zseni.weatherapp.domain.weather.WeatherType

@Entity(tableName = "weather_data")
data class WeatherEntity(
    @PrimaryKey val id:Int = 1,
    val background:Int ,
    val sunrise:String,
    val sunset:String,
    val temperature:Double,
    val feelsLike:Double,
    val pressure:Int,
    val humidity:Int,
    val visibility:Int,
    val uvi:Double,
    val windSpeed:Double,
    val windDegree:Int,
    val weather:String,
    val forecasts:String
)

