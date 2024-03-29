package com.zseni.weatherapp.data.local.mappers

import android.util.Log
import androidx.annotation.DrawableRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zseni.weatherapp.R
import com.zseni.weatherapp.data.local.room.WeatherEntity
import com.zseni.weatherapp.domain.model.DailyForecast
import com.zseni.weatherapp.domain.model.Weather
import com.zseni.weatherapp.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow



object WeatherEntityMapper {
    fun mapWeatherEntityToWeatherData(weatherEntity: WeatherEntity): WeatherData {
        Log.i("Weather", "WEATHER")
        val currentWeather = Weather(
            sunrise = weatherEntity.sunrise ?: "",
            sunset = weatherEntity.sunset ?: "",
            temperature = weatherEntity.temperature ?: 0.0,
            feelsLike = weatherEntity.feelsLike ?: 0.0,
            pressure = weatherEntity.pressure ?: 0,
            humidity = weatherEntity.humidity ?: 0,
            visibility = weatherEntity.visibility ?: 0,
            uvi = weatherEntity.uvi ?: 0.0,
            windSpeed = weatherEntity.windSpeed ?: 0.0,
            windDegree = weatherEntity.windDegree ?: 0,
            weather = weatherEntity.weather ?: "",
        )
        Log.i("entity null", "where is the NPE")
        return WeatherData(
            background = getBackgroundFromId(weatherEntity.background),
            current = currentWeather,
            forecasts = convertJsonToList(weatherEntity.forecasts ?: "")
        )
    }

    fun mapWeatherDataToWeatherEntity(weatherData: WeatherData): WeatherEntity {
        val currentWeather = weatherData.current
        return WeatherEntity(
            background = getIdFromBackground(weatherData.background),
            sunrise = currentWeather.sunrise,
            sunset = currentWeather.sunset,
            temperature = currentWeather.temperature,
            feelsLike = currentWeather.feelsLike,
            pressure = currentWeather.pressure,
            humidity = currentWeather.humidity,
            visibility = currentWeather.visibility,
            uvi = currentWeather.uvi,
            windSpeed = currentWeather.windSpeed,
            windDegree = currentWeather.windDegree,
            weather = currentWeather.weather,
            forecasts = convertListToJson(weatherData.forecasts)

        )

    }

    @DrawableRes
    private fun getBackgroundFromId(id: Int?): Int {
        return when (id) {
            0 -> R.drawable.bg_morning
            1 -> R.drawable.bg_day
            2 -> R.drawable.bg_evening
            else -> R.drawable.bg_night
        }
    }

    private fun getIdFromBackground(@DrawableRes id: Int): Int {
        return when (id) {
            R.drawable.bg_morning -> 0
            R.drawable.bg_day -> 1
            R.drawable.bg_evening -> 2
            else -> 3
        }
    }

    private fun convertListToJson(forecasts: List<DailyForecast>): String {
        val jsonList = forecasts.map { forecast ->
            forecast.copy(icon = getIdFromIcon(forecast.icon))
        }
        return Gson().toJson(jsonList)
    }

    private fun convertJsonToList(forecasts: String): List<DailyForecast> {
        val type = object : TypeToken<List<DailyForecast>>() {}.type
        val jsonList: List<DailyForecast> = Gson().fromJson(forecasts, type)
        return jsonList.map { forecast ->
            forecast.copy(icon = getIconFromId(forecast.icon))
        }
    }

    @DrawableRes
    private fun getIconFromId(id: Int): Int {
        return when (id) {
            0 -> R.drawable.ic_clear_day
            1 -> R.drawable.ic_cloudy_day
            2 -> R.drawable.ic_rain
            3 -> R.drawable.ic_thunderstorm
            4 -> R.drawable.ic_snow
            5 -> R.drawable.ic_fog
            else -> R.drawable.ic_tornado
        }
    }

    private fun getIdFromIcon(@DrawableRes id: Int): Int {
        return when (id) {
            R.drawable.ic_clear_day -> 0
            R.drawable.ic_cloudy_day -> 1
            R.drawable.ic_rain -> 2
            R.drawable.ic_thunderstorm -> 3
            R.drawable.ic_snow -> 4
            R.drawable.ic_fog -> 5
            else -> 6
        }
    }
}





