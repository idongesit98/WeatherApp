package com.zseni.weatherapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zseni.weatherapp.util.CityConverter
import com.zseni.weatherapp.util.CloudsConverter
import com.zseni.weatherapp.util.Converters
import com.zseni.weatherapp.util.CoordConverter
import com.zseni.weatherapp.util.MainConverter
import com.zseni.weatherapp.util.RainConverter
import com.zseni.weatherapp.util.SysConverter
import com.zseni.weatherapp.util.WeatherConverters
import com.zseni.weatherapp.util.WindConverter

@Database(entities = [WeatherEntity::class, ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class, SysConverter::class,WindConverter::class,RainConverter::class,
    MainConverter::class,CoordConverter::class,CloudsConverter::class,CityConverter::class,WeatherConverters::class
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): WeatherDao
}