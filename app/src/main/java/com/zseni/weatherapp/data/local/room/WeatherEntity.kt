package com.zseni.weatherapp.data.local.room

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zseni.weatherapp.domain.weather.WeatherType

@Entity(tableName = "weather_data")
data class WeatherEntity(
    @PrimaryKey val id:Int = 1,
    val background:Int? = null ,
    val sunrise:String? = null,
    val sunset:String? = null,
    val temperature:Double? = null,
    val feelsLike:Double? = null,
    val pressure:Int? = null,
    val humidity:Int? = null,
    val visibility:Int? = null,
    val uvi:Double? = null,
    val windSpeed:Double? = null,
    val windDegree:Int? = null,
    val weather:String? = null,
    val forecasts:String? = null
)

