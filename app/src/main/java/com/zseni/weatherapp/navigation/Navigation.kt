package com.zseni.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zseni.weatherapp.presentation.component.WeatherState

/**
TODO: Since you are using navigation bring all the screens inside and then
 this function in the main activity
**/

@Composable
fun WeatherNav(
    navController: NavHostController){
    val state = WeatherState()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"){

        composable("weather card"){
            //WeatherCard(state = state )
        }

    }
}