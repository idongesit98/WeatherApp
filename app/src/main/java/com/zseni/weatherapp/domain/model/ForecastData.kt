package com.zseni.weatherapp.domain.model

import com.zseni.weatherapp.data.api.remote.forecastweather.City
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDescription

data class ForecastData(
    val id:Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastDescription>,
    val message: Int
)