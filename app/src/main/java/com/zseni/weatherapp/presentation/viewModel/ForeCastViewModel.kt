package com.zseni.weatherapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zseni.weatherapp.domain.location.LocationTracker
import com.zseni.weatherapp.domain.repository.WeatherRepository
import com.zseni.weatherapp.presentation.component.ForeCastState
import com.zseni.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ForeCastViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel() {
    private var _foreCastState = MutableStateFlow(ForeCastState())
    val foreCastState: StateFlow<ForeCastState> = _foreCastState.asStateFlow()
     var selectedDate:LocalDate = LocalDate.now()

    init {
        getForeCastInfo()
    }

    fun getForeCastInfo(){
        _foreCastState.value = _foreCastState.value.copy(isLoading = true)
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let {location->
                Log.d("ForeCastViewModel", "Location: $location")
                repository.getForeCastData(location.latitude,location.longitude)
                    .collect{foreCastData->
                        when(foreCastData){
                            is Resource.Success ->{
                                val data = foreCastData.data
                                Log.d("ForeCastViewModel", "API Response(Success): $data")
                                _foreCastState.value = _foreCastState.value.copy(
                                    foreCastInfo = data,
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                Log.e("ForeCastViewModel", "API Response (Error): ${foreCastData.message}")
                            }
                            is Resource.Loading -> {
                                Log.d("ForeCastViewModel", "API Response (Loading)")
                            }
                        }
                    }
            }
        }
        _foreCastState.value = _foreCastState.value.copy(isLoading = false)
    }

    fun updateSelectedDate(newDate: LocalDate){
        selectedDate = newDate
    }

}