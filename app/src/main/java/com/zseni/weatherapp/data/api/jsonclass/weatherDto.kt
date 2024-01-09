package com.zseni.weatherapp.data.api.jsonclass

import com.google.gson.annotations.SerializedName

//TODO: stop using Any as your datatypes, it can be the cause of the crash, view the data and use the real datatypes
data class weatherDto(
    val alerts: List<Alerts>,
    val current: Current,
    val daily: List<DailyWeather>,// changed this to DailyWeather from Any
    val hourly: List<HourlyWeather>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezone_offset: Int
)