package com.zseni.weatherapp.data.api.jsonclass

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

//TODO: you should not have all the data models in one place, this is not right, seperate all of them into seperate files

data class DailyWeather(
    val dt: Long,
    val temp: Double,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonSet: Long,
    val moonPhase: Double,
    val summary: String,
    @SerializedName("feels_like")
    val feelsLike: Double,
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

data class HourlyWeather(
    val dt: Long,
    val temp: Double,
    val weather: List<Weather>,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerialName("wind_deg")
    val windDeg: Int,
    @SerialName("wind_gust")
    val windGust: Double,
)

data class Minutely(
    val dt:Long,
    val pt:Int
)


