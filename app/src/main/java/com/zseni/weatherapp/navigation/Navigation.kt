package com.zseni.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zseni.weatherapp.presentation.ForeCastScreen
import com.zseni.weatherapp.presentation.WeatherScreen
import com.zseni.weatherapp.presentation.viewModel.WeatherViewModel
import com.zseni.weatherapp.ui.theme.containerColour

/**
TODO: Since you are using navigation bring all the screens inside and then
 this function in the main activity
**/

@Composable
fun WeatherNav(){
    val navController = rememberNavController()
    val viewModel:WeatherViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.WeatherScreen.route){

        composable(Screen.WeatherScreen.route){
            WeatherScreen(
                backgroundColour = containerColour,
                navController = navController,
                refreshWeather = viewModel::refreshWeather
            )
        }

        composable(Screen.ForeCastScreen.route){
            ForeCastScreen()
        }
    }
}