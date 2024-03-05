package com.zseni.weatherapp.data.api.remote.forecastweather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)