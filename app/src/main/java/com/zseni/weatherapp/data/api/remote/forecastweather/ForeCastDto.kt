package com.zseni.weatherapp.data.api.remote.forecastweather

data class ForeCastDto(
    val id:Int,
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForeCastDescription>,
    val message: Int
)