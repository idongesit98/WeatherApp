package com.zseni.weatherapp.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.zseni.weatherapp.navigation.WeatherNav
import com.zseni.weatherapp.presentation.WeatherViewModel
import com.zseni.weatherapp.presentation.component.WeatherCard
import com.zseni.weatherapp.presentation.component.WeatherState
import com.zseni.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:WeatherViewModel by viewModels()
    private lateinit var  permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                ){
                    WeatherCard(
                        //TODO: Create your viewModel and add the state here
                        state =  WeatherState(),
                        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary))
                    WeatherNav(navController = rememberNavController())

                }


//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    WeatherNav(navController = rememberNavController())
//                }

            }
        }
    }


}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val state = WeatherState()
    //SplashScreen()
    WeatherCard(state = state)
}