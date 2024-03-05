package com.zseni.weatherapp.data.api

import com.zseni.weatherapp.BuildConfig
import com.zseni.weatherapp.data.api.remote.currentweather.CurrentWeatherDto
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
   @GET("weather?")
   suspend fun getWeatherData(
       @Query("lat") latitude:Double,
       @Query("lon") longitude:Double,
       @Query("units") units:String,
       @Query("appid") apiKey:String = BuildConfig.OPENAPI_KEY,
   ): Response<CurrentWeatherDto>

   @GET("forecast?")
   suspend fun getForecastData(
       @Query("lat") latitude:Double,
       @Query("lon") longitude:Double,
       @Query("units") units:String,
       @Query("appid") apiKey:String = BuildConfig.OPENAPI_KEY
   ):Response<ForeCastDto>
}