package com.zseni.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.zseni.weatherapp.R
import com.zseni.weatherapp.navigation.Screen
import com.zseni.weatherapp.presentation.component.DataCard
import com.zseni.weatherapp.presentation.viewModel.WeatherViewModel
import com.zseni.weatherapp.ui.theme.ReemKufi
import com.zseni.weatherapp.ui.theme.blurColor
import com.zseni.weatherapp.ui.theme.cardTextColour
import com.zseni.weatherapp.ui.theme.poppins
import com.zseni.weatherapp.util.AppComponents
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * This is a reusable screen Ui component that handles the Ui of the home that
 * contains the weather image, and other components on the first screen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    backgroundColour: Color,
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
    refreshWeather:() -> Unit,
    navController: NavController
) {

    val simpleTime = SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(Date())
    val state = viewModel.uiState.collectAsState().value
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember{ mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        refreshWeather()
        refreshing = false
    }
    val feedIsLoading = state.isLoading
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing, ::refresh
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        state.weatherInfo?.let { data ->
            Column(
                modifier = Modifier
                    .background(blurColor)
                    .fillMaxSize()
            ) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 30.dp
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 2.dp, end = 2.dp, bottom = 90.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColour
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Today:${simpleTime}",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontFamily = poppins,
                            color = cardTextColour,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = data.name,
                            textAlign = TextAlign.Center,
                            color = cardTextColour,
                            fontFamily = poppins,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (data.weather.isNotEmpty()) {
                            val icon = data.weather[0].icon
                            AsyncImage(
                                model = "${AppComponents.ICON_URL}${icon}.png",
                                contentDescription = "",
                                modifier = Modifier.size(200.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "${data.main.temp.toString()}°C",
                            fontSize = 70.sp,
                            color = cardTextColour,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        if (data.weather.isNotEmpty()) {
                            val description = data.weather[0].description
                            Text(
                                text = description.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                },
                                fontFamily = ReemKufi,
                                fontSize = 30.sp,
                                color = cardTextColour,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            DataCard()
                        }
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        shape = MaterialTheme.shapes.small,
                        onClick = { navController.navigate(Screen.ForeCastScreen.route) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.see_forecast),
                            fontFamily = poppins,
                            fontSize = 18.sp,
                        )
                    }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = feedIsLoading,
            state = refreshState,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 60.dp)
        )
    }

}

@Preview
@Composable
fun PreviewWeatherCard() {
//    WeatherScreen(backgroundColour = Color.Black, weatherData = WeatherData())
}

//Row(
//modifier = Modifier.fillMaxWidth(),
//horizontalArrangement = Arrangement.SpaceAround
//) {
//    WeatherDataDisplay(
//        value = data.main.pressure,
//        unit = "hpa",
//        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
//        iconTint = Color.White,
//        textStyle = TextStyle(color = Color.White),
//        variable = "pressure"
//    )
//    WeatherDataDisplay(
//        value = data.main.humidity,
//        unit = "%",
//        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
//        iconTint = Color.White,
//        textStyle = TextStyle(color = Color.White),
//        variable = "humidity"
//    )
//    WeatherDataDisplay(
//        value = data.wind.speed?.toInt(),
//        unit = "km/hr",
//        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
//        iconTint = Color.White,
//        textStyle = TextStyle(color = Color.White),
//        variable = "wind"
//    )
//}