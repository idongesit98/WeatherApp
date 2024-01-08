package com.zseni.weatherapp.data.testingData

import com.google.gson.annotations.SerializedName
//TODO: stop using Any as your datatypes, it can be the cause of the crash, view the data and use the real datatypes
data class weatherDto(
    val alerts: List<Any>,
    val current: Current,
    val daily: List<DailyWeather>,// changed this to DailyWeather from Any
    val hourly: List<Any>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Any>,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezone_offset: Int
)