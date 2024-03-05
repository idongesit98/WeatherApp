package com.zseni.weatherapp.navigation

sealed class Screen(val route:String) {
    data object WeatherScreen:Screen("weatherScreen")
    data object ForeCastScreen:Screen("foreCastScreen")
}