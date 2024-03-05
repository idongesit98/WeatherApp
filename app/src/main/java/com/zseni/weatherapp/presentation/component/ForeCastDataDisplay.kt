package com.zseni.weatherapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.zseni.weatherapp.data.local.mappers.filteredWeatherForecastByDay
import com.zseni.weatherapp.presentation.viewModel.ForeCastViewModel
import com.zseni.weatherapp.ui.theme.ReemKufi
import com.zseni.weatherapp.ui.theme.cardTextColour
import com.zseni.weatherapp.ui.theme.containerColour
import com.zseni.weatherapp.ui.theme.poppins
import com.zseni.weatherapp.util.AppComponents
import com.zseni.weatherapp.util.getDifferences
import java.time.LocalDate

@Composable
fun ForecastCard(
    foreModel: ForeCastViewModel = hiltViewModel(),
) {
    val currentDate = remember {
        LocalDate.now()
    }
    var selectedDate by remember { mutableStateOf(currentDate) }
    val selection = remember {
        mutableStateOf(currentDate)
    }
    val dayNo = selection.getDifferences(LocalDate.now())
    val foreState = foreModel.foreCastState.collectAsState().value
    foreState.foreCastInfo?.let {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CalenderCard(
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                onDateSelected = {newSelectedDate ->
                    selectedDate = newSelectedDate
                    foreModel.getForeCastInfo()
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (dayNo in 0..5) {
                it.filteredWeatherForecastByDay(dayNo).let { filteredList ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxHeight(),
                            contentPadding = PaddingValues(bottom = 16.dp),
                            content = {
                                itemsIndexed(filteredList) { index, item ->
                                    if (index != 0) {
                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                    it.list.forEach { description ->
                                        if (description.weather.isNotEmpty()) {
                                            ForecastDisplay(
                                                cityName = it.city.name,
                                                temp = item.main.temp.toString(),
                                                dateAndTime = item.dt_txt.toString(),
                                                description = description.weather[0].description.replaceFirstChar { it.titlecase() },
                                                feelsLike = description.main.feels_like.toString().replaceFirstChar { it.titlecase() },
                                                tempMax = item.main.temp_max.toString().replaceFirstChar { it.titlecase() },
                                                pressure = item.main.pressure.toString(),
                                                humidity = item.main.humidity.toString().replaceFirstChar { it.titlecase() }
                                            )
                                        }
                                    }

                                }
                            }
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun ForecastDisplay(
    cityName: String,
    temp: String,
    dateAndTime: String?,
    description: String,
    feelsLike:String,
    humidity:String,
    pressure:String,
    tempMax:String,
    foreModel: ForeCastViewModel = hiltViewModel(),
) {
    val foreState = foreModel.foreCastState.collectAsState().value
    foreState.foreCastInfo?.let { foreData ->
            Card(
                modifier = Modifier
                    .padding(start = 4.dp, top = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = containerColour
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Column(modifier = Modifier
                    .padding(start = 15.dp, end = 5.dp)
                ) {
                    Text(
                        text = cityName,
                        fontFamily = poppins,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = cardTextColour,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Temperature:${temp}°C",
                        fontFamily = ReemKufi,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = cardTextColour
                    )

                    Text(
                        text = "Temperature Max:${tempMax}°C",
                        fontFamily = ReemKufi,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = cardTextColour
                    )

                    Text(
                        text = "Humidity:${humidity}°C",
                        fontFamily = ReemKufi,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = cardTextColour
                    )

                    Text(
                        text = "Pressure:${pressure}mm/hg",
                        fontFamily = ReemKufi,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = cardTextColour
                    )

                    Row(
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Description:${description}",
                            fontFamily = ReemKufi,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = cardTextColour,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "FeelsLike${feelsLike}°C",
                            fontFamily = ReemKufi,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = cardTextColour,
                        )
                        if (foreData.list.isNotEmpty()) {
                            val icon = foreData.list[0].weather[0].icon
                            AsyncImage(
                                model = "${AppComponents.ICON_URL}${icon}.png",
                                contentDescription = "",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                    if (dateAndTime != null) {
                        Text(
                            text = dateAndTime,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = cardTextColour,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
    }
}


@Preview
@Composable
fun PreviewForeCast() {
    ForecastDisplay(
        cityName = "Uyo",
        temp = "36.0°C",
        dateAndTime = "18/08/2023 06:00:00 Pm",
        description = "Overcast Cloudy",
        feelsLike = "36.0°C",
        humidity = "36.0%",
        pressure = "160 mm/hg",
        tempMax = "36.0°C"
    )
}

