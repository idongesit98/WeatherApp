package com.zseni.weatherapp.presentation


//import androidx.lifecycle.viewModelScope
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zseni.weatherapp.domain.location.LocationTracker
import com.zseni.weatherapp.domain.repository.WeatherRepository
import com.zseni.weatherapp.presentation.component.WeatherState
import com.zseni.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository:WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel() {

     private var _uiState = MutableStateFlow(WeatherState())
    val uiState: StateFlow<WeatherState> = _uiState.asStateFlow()



    init {
        getWeatherInfo()}

     fun getWeatherInfo(){
        _uiState.value = _uiState.value.copy(isLoading = true)
         Log.d("WeatherData","All data")
        viewModelScope.launch {
            Log.d("WeatherData","All data")
            locationTracker.getCurrentLocation()?.let {location ->
                repository.getWeatherData(location.latitude,location.longitude)
                    .collect{ weatherData ->
                        _uiState.value = _uiState.value.copy(
                            weatherInfo = weatherData.data,
                            isLoading = false
                        )
                    }
                Log.d("WeatherData","All data")
            }
        }
        _uiState.value = _uiState.value.copy(isLoading = false )
    }





}





