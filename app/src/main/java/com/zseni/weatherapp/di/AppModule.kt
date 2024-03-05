package com.zseni.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.zseni.weatherapp.data.api.RemoteDataSource
import com.zseni.weatherapp.data.api.RemoteDataSourceImpl
import com.zseni.weatherapp.data.api.WeatherApiService
import com.zseni.weatherapp.data.local.room.AppDatabase
import com.zseni.weatherapp.data.local.room.LocalDataSource
import com.zseni.weatherapp.data.local.room.LocalDataSourceImpl
import com.zseni.weatherapp.data.local.room.WeatherDao
import com.zseni.weatherapp.util.AppComponents.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideWeatherApiService(
       okHttpClient: OkHttpClient
    ): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
    //TODO: Added a database instance to the DI
    @Provides
    @Singleton
    fun providesDatabase(context:Application):AppDatabase{
        return Room.databaseBuilder(
            context,AppDatabase::class.java, "weather.db")
            .build()
    }

//TODO: your interceptor was logged before, you have all the data you need to find the cause of the crash
    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: Provider<HttpLoggingInterceptor>
    ) = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor.get())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app:Application):FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }


   @Provides
   @Singleton
   fun provideIoDispatcher():CoroutineDispatcher = Dispatchers.IO

    @Provides
    @IoDispatcher
    fun provideIoDispatcherForLocal(@IoDispatcher ioDispatcher: CoroutineDispatcher): CoroutineDispatcher =
        ioDispatcher


    @Provides
    fun provideRemoteDataSource(weatherApiService: WeatherApiService):RemoteDataSource{
        return RemoteDataSourceImpl(weatherApiService,Dispatchers.IO)
    }

    @Provides
    fun providesWeatherDao(appDatabase: AppDatabase):WeatherDao{
        return appDatabase.getDao()
    }


    //TODO: Added LocalDatasource to DI
    @Provides
    fun provideLocalDataSource(weatherDao: WeatherDao):LocalDataSource{
        return LocalDataSourceImpl(weatherDao,Dispatchers.IO)
    }
}