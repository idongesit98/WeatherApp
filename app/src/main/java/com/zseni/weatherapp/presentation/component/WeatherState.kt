package com.zseni.weatherapp.presentation.component

import com.zseni.weatherapp.domain.model.ForecastData
import com.zseni.weatherapp.domain.model.WeatherData


data class WeatherState(
    val weatherInfo: WeatherData? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)

data class ForeCastState(
    val foreCastInfo:ForecastData? = null,
    val isLoading:Boolean = false,
    val error:String? = null
)

