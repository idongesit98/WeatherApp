package com.zseni.weatherapp.data.testingData

import com.google.gson.annotations.SerializedName
import com.zseni.weatherapp.data.api.FeelsLike
import com.zseni.weatherapp.data.api.Temp

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,// changed go int
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Long, //changed both to sunrise and sunset to Long from Int
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

data class DailyWeather(
    val dt: Long,
    val temp: Temp,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
   val moonSet: Long,
    val moonPhase: Double,
    val summary: String,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_deg")
    val windDeg: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    val clouds: Int,
    val pop: Double,
    val uvi: Double,
    val weather: List<Weather>,
)