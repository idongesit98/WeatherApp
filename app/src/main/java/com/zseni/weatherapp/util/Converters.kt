package com.zseni.weatherapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zseni.weatherapp.data.api.remote.currentweather.Clouds
import com.zseni.weatherapp.data.api.remote.currentweather.Coord
import com.zseni.weatherapp.data.api.remote.currentweather.Main
import com.zseni.weatherapp.data.api.remote.currentweather.Rain
import com.zseni.weatherapp.data.api.remote.currentweather.Sys
import com.zseni.weatherapp.data.api.remote.currentweather.WeatherDescription
import com.zseni.weatherapp.data.api.remote.currentweather.Wind
import com.zseni.weatherapp.data.api.remote.forecastweather.City
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDescription
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()
    private val type: Type = object :TypeToken<List<WeatherDescription?>?>() {}.type

    @TypeConverter
    fun fromWeatherDtoList(list: List<WeatherDescription?>?):String{
        return gson.toJson(list,type)
    }

    @TypeConverter
    fun toWeatherDtoList(json:String?):List<WeatherDescription>{
        return gson.fromJson(json,type)
    }
}

class SysConverter {

    @TypeConverter
    fun fromJson(json: String): Sys {
        return Gson().fromJson(json, Sys::class.java)
    }

    @TypeConverter
    fun toJson(sys: Sys): String {
        return Gson().toJson(sys)
    }
}

class CloudsConverter {
    @TypeConverter
    fun fromJson(json: String): Clouds {
        return Gson().fromJson(json, Clouds::class.java)
    }

    @TypeConverter
    fun toJson(clouds: Clouds): String {
        return Gson().toJson(clouds)
    }
}

class MainConverter {
    @TypeConverter
    fun fromJson(json: String): Main {
        return Gson().fromJson(json, Main::class.java)
    }

    @TypeConverter
    fun toJson(main: Main): String {
        return Gson().toJson(main)
    }
}

class RainConverter {
    @TypeConverter
    fun fromJson(json: String): Rain {
        return Gson().fromJson(json, Rain::class.java)
    }

    @TypeConverter
    fun toJson(rain: Rain): String {
        return Gson().toJson(rain)
    }
}

class WindConverter {
    @TypeConverter
    fun fromJson(json: String): Wind {
        return Gson().fromJson(json, Wind::class.java)
    }

    @TypeConverter
    fun toJson(wind: Wind): String {
        return Gson().toJson(wind)
    }
}

class CoordConverter {
    @TypeConverter
    fun fromJson(json: String): Coord {
        return Gson().fromJson(json, Coord::class.java)
    }

    @TypeConverter
    fun toJson(coord: Coord): String {
        return Gson().toJson(coord)
    }
}

class CityConverter {
    @TypeConverter
    fun fromJson(json: String): City {
        return Gson().fromJson(json, City::class.java)
    }

    @TypeConverter
    fun toJson(city: City): String {
        return Gson().toJson(city)
    }
}

class WeatherConverters {
    private val gson = Gson()
    private val type: Type = object :TypeToken<List<ForeCastDescription?>?>() {}.type

    @TypeConverter
    fun foreCastDescription(list: List<ForeCastDescription?>?):String{
        return gson.toJson(list,type)
    }

    @TypeConverter
    fun toForeCast(json:String?):List<ForeCastDescription>{
        return gson.fromJson(json,type)
    }
}
