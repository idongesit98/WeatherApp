package com.zseni.weatherapp.data.api.jsonclass

import com.google.gson.annotations.SerializedName

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_gust")
    val wind_gust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)
