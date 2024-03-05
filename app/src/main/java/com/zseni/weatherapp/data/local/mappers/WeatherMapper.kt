package com.zseni.weatherapp.data.local.mappers

import android.util.Log
import com.zseni.weatherapp.data.api.remote.currentweather.CurrentWeatherDto
import com.zseni.weatherapp.data.api.remote.currentweather.Rain
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDescription
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDto
import com.zseni.weatherapp.data.local.room.ForecastEntity
import com.zseni.weatherapp.data.local.room.WeatherEntity
import com.zseni.weatherapp.domain.model.ForecastData
import com.zseni.weatherapp.domain.model.WeatherData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun CurrentWeatherDto.toEntity(lastFetchedTime: Long):WeatherEntity{
    return WeatherEntity(
        id = id?:1,
        base = base?:"",
        clouds = clouds,
        cod = cod?: 1,
        coord = coord,
        dt = lastFetchedTime.toInt(),
        main = main,
        name = name?:"",
        rain = rain?: Rain(0.0),
        sys = sys,
        timezone = timezone?:1,
        visibility = visibility?:1,
        weather = weather?: listOf(),
        wind = wind,
    )
}

fun WeatherEntity.toWeatherDto(): CurrentWeatherDto {
    return CurrentWeatherDto(
        base = base,
        clouds = clouds,
        cod = cod,
        coord = coord,
        dt = dt,
        id = id,
        main = main,
        name = name,
        rain = rain,
        sys = sys,
        timezone = timezone,
        visibility = visibility,
        weather = weather,
        wind = wind,
    )
}

fun WeatherEntity.toWeatherData():WeatherData = WeatherData(
    base = base,
    clouds = clouds,
    cod = cod,
    coord = coord,
    dt = dt,
    id = id,
    main = main,
    name = name,
    rain = rain,
    sys = sys,
    weather = weather,
    visibility = visibility,
    wind = wind,
    timezone = timezone,
)


fun ForecastEntity.toForeCastData():ForecastData {
    return ForecastData(
        id = id,
        city = city,
        cnt = cnt,
        cod = cod,
        list = list,
        message = message
    )
}
fun ForeCastDto.toEntity(lastFetchedTime: Long):ForecastEntity{
    return ForecastEntity(
        id = id,
        city = city,
        cnt = cnt,
        message = message,
        list = list,
        cod = cod
    )
}

fun ForecastData.filteredWeatherForecastByDay(selectedDay:Int): List<ForeCastDescription> {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, selectedDay)
    val checkerDay = calendar.get(Calendar.DAY_OF_MONTH)
    val checkerMonth = calendar.get(Calendar.MONTH)
    val checkerYear = calendar.get(Calendar.YEAR)
    Log.d("FilterLog", "Checker: $checkerDay-$checkerMonth-$checkerYear")

    val filteredList = this.list.filter { weatherForecast ->
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val formattedDate = format.parse(weatherForecast.dt_txt?:"")
        if (formattedDate != null) {
            calendar.time = formattedDate
        }

        val weatherForecastDay = calendar.get(Calendar.DAY_OF_MONTH)
        val weatherForecastMonth = calendar.get(Calendar.MONTH)
        val weatherForecastedYear = calendar.get(Calendar.YEAR)
        Log.d("FilterLog", "Forecast: $weatherForecastDay-$weatherForecastMonth-$weatherForecastedYear")
        weatherForecastDay == checkerDay && weatherForecastMonth == checkerMonth && weatherForecastedYear == checkerYear

    }
    Log.d("FilterLog", "Original list size: ${this.list.size}")
    return filteredList
}










