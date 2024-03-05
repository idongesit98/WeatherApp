package com.zseni.weatherapp.data.api


import com.zseni.weatherapp.data.api.remote.currentweather.CurrentWeatherDto
import com.zseni.weatherapp.data.api.remote.forecastweather.ForeCastDto
import com.zseni.weatherapp.di.IoDispatcher
import com.zseni.weatherapp.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoteDataSource{
    suspend fun getWeather(latitude: Double,longitude: Double):Resource<CurrentWeatherDto>

    suspend fun getForeCast(latitude: Double,longitude: Double): Resource<ForeCastDto> // city:Int
}
class RemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
):RemoteDataSource {
    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeatherDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                Resource.Success(
                    data = weatherApi.getWeatherData(latitude, longitude,"metric"
                    ).body()
                )
            }catch (e:Exception){
                Resource.Error(null, e.toString())
            }
        }

    override suspend fun getForeCast(
        latitude: Double,
        longitude: Double,
    ): Resource<ForeCastDto> =
        withContext(ioDispatcher){
            return@withContext try {
                Resource.Success(
                    data = weatherApi.getForecastData(latitude,
                        longitude,"metric").body()
                )
            }catch (e:Exception){
                Resource.Error(null,e.toString())
            }
        }
}