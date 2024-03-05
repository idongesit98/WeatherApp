package com.zseni.weatherapp.domain.model

import com.zseni.weatherapp.data.api.remote.currentweather.Clouds
import com.zseni.weatherapp.data.api.remote.currentweather.Coord
import com.zseni.weatherapp.data.api.remote.currentweather.Main
import com.zseni.weatherapp.data.api.remote.currentweather.Rain
import com.zseni.weatherapp.data.api.remote.currentweather.Sys
import com.zseni.weatherapp.data.api.remote.currentweather.WeatherDescription
import com.zseni.weatherapp.data.api.remote.currentweather.Wind
data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val rain: Rain,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDescription>,
    val wind: Wind,
)

