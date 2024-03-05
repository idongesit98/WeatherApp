package com.zseni.weatherapp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zseni.weatherapp.data.api.remote.currentweather.Clouds
import com.zseni.weatherapp.data.api.remote.currentweather.Coord
import com.zseni.weatherapp.data.api.remote.currentweather.Main
import com.zseni.weatherapp.data.api.remote.currentweather.Rain
import com.zseni.weatherapp.data.api.remote.currentweather.Sys
import com.zseni.weatherapp.data.api.remote.currentweather.WeatherDescription
import com.zseni.weatherapp.data.api.remote.currentweather.Wind
import com.zseni.weatherapp.data.api.remote.forecastweather.City
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDescription

@Entity(tableName = "weather_data")
data class WeatherEntity(
    @PrimaryKey val id:Int = 0,
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDescription>,
    val wind: Wind,
)

@Entity(tableName = "weather_forecasts")
data class ForecastEntity(
    @PrimaryKey
    val id:Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastDescription>,
    val message: Int

)


