package com.zseni.weatherapp.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zseni.weatherapp.R
import com.zseni.weatherapp.presentation.viewModel.ForeCastViewModel
import com.zseni.weatherapp.util.CalenderDataSource
import com.zseni.weatherapp.util.CalenderUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun Header(
    data:CalenderUiModel,
    onPrevClickListener:(LocalDate) -> Unit,
    onNextClickListener:(LocalDate) -> Unit
){
    Row {
        Text(
            text = if (data.selectedDate.isToday){"Today"}else data.selectedDate.date
                .format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                ),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(
            onClick = {
                data.startDate.date
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.previous)
            )
        }
        IconButton(
            onClick = { data.endDate.date }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = stringResource(R.string.forward)
            )
        }
    }
}
@Composable
fun Content(
    data: CalenderUiModel,
    onDateClickListener:(CalenderUiModel.Date)->Unit
){
    LazyRow{
        items(items = data.visibleDates){ date ->
            ContentItem(
                date = date,
                onDateClickListener
            )
        }
    }
}
@Composable
fun ContentItem(
    date: CalenderUiModel.Date,
    onClickListener:(CalenderUiModel.Date) -> Unit
){
    Card(modifier = Modifier
        .padding(vertical = 4.dp, horizontal = 4.dp)
        .clickable { onClickListener(date) },
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected){
                MaterialTheme.colorScheme.primary
            }else {
                    MaterialTheme.colorScheme.secondary
            }
        )
    ) {
        Column(modifier = Modifier
            .width(40.dp)
            .height(48.dp)
            .padding(4.dp)
        ) {
            Text(
                text = date.day,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        }
    }

}

@Composable
fun CalenderCard(
    modifier: Modifier = Modifier,
    onDateSelected:(LocalDate) -> Unit,
    foreModel: ForeCastViewModel = hiltViewModel(),
){
    val dataSource = CalenderDataSource()
    var calenderUiModel by remember{ mutableStateOf(dataSource.getData(lastSelected = dataSource.today)) }

    Column(modifier = modifier.fillMaxWidth()) {
        Header(
            data = calenderUiModel,
            onPrevClickListener = {startDate ->
                val finalStartDate = startDate.minusDays(1)
                foreModel.updateSelectedDate(finalStartDate)
                foreModel.getForeCastInfo()
                calenderUiModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelected = calenderUiModel.selectedDate.date)
            },
            onNextClickListener = {endDate ->
                val finalStartDate = endDate.plusDays(2)
                foreModel.updateSelectedDate(finalStartDate)
                foreModel.getForeCastInfo()
                calenderUiModel = dataSource.getData(
                    startDate = finalStartDate,
                    lastSelected = calenderUiModel.selectedDate.date
                )
            }
        )
        Content(
            data = calenderUiModel,
            onDateClickListener = {date ->
                calenderUiModel = calenderUiModel.copy(
                    selectedDate = date,
                    visibleDates = calenderUiModel.visibleDates.map {
                        it.copy(
                            isSelected = it.date.isEqual(date.date)
                        )
                    }
                )
                onDateSelected(date.date)
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCalender() {
    CalenderCard(
        modifier = Modifier.padding(16.dp),
        onDateSelected = {}
    )
}