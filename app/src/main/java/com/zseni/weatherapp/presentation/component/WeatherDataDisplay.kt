package com.zseni.weatherapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zseni.weatherapp.R
import com.zseni.weatherapp.presentation.viewModel.WeatherViewModel
import com.zseni.weatherapp.ui.theme.blurColor
import com.zseni.weatherapp.ui.theme.cardTextColour
import com.zseni.weatherapp.ui.theme.poppins

@Composable
fun DataCard(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    state.weatherInfo?.let { data ->
        Card(
            modifier = Modifier
                .padding(start = 4.dp, top = 15.dp),
            colors = CardDefaults.cardColors(
                containerColor = blurColor,
            ),
            shape = MaterialTheme.shapes.small
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, top = 5.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    value = data.wind.speed?.toInt(),
                    unit = "km/hr",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    variable = stringResource(R.string.wind)
                )
                WeatherDataDisplay(
                    value = data.main.pressure,
                    unit = "hpa",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    variable = stringResource(R.string.pressure)
                )
                WeatherDataDisplay(
                    value = data.main.humidity,
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    variable = stringResource(R.string.humidity),
                )
            }


        }
    }


}

@Composable
fun WeatherDataDisplay(
    value: Int?,
    unit: String,
    variable: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    iconTint: Color = Color.White,
) {
    Column {
        Row(
            modifier = modifier.padding(start = 4.dp, top = 10.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = "$value$unit",
                style = textStyle,
                textAlign = TextAlign.Justify,
                fontFamily = poppins,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold,
                color = cardTextColour
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = variable,
                style = textStyle,
                textAlign = TextAlign.Right,
                fontFamily = poppins,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold,
                color = cardTextColour
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}


@Preview
@Composable
fun PreviewDataCard() {
    val viewModel: WeatherViewModel = hiltViewModel()
    DataCard(
        viewModel,
    )
}

@Preview
@Composable
fun PreviewWeatherDataDisplay() {
    WeatherDataDisplay(
        value = 1,
        unit = "",
        icon = ImageVector.vectorResource(R.drawable.ic_clear_day),
        variable = ""
    )
}
