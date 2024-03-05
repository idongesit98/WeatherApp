package com.zseni.weatherapp.data.api.remote.currentweather

data class WeatherDescription(
    val description: String,
    val icon: String,
    val id: Int?,
    val main: String?
)