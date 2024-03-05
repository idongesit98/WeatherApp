package com.zseni.weatherapp.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

data class CalenderUiModel(
    val selectedDate:Date, //This shows date selected by the user. by default today
    val visibleDates: List<Date> // The dates shown on the screen
){
    val startDate:Date = visibleDates.first() //The first visible date
    val endDate:Date = visibleDates.last() //The last visible date

    data class Date(
        val date:LocalDate,
        val isSelected:Boolean,
        val isToday:Boolean,
        val latitude:Double,
        val longitude: Double
    ){
        val day:String = date.format(DateTimeFormatter.ofPattern("E")) // Get the date by formatting the date
    }
}

class CalenderDataSource{
    val today:LocalDate
        get()  {
            return LocalDate.now()
        }

    fun getData(startDate:LocalDate = today, lastSelected:LocalDate):CalenderUiModel{
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayOfWeek,endDayOfWeek)
        return toUiModel(visibleDates,lastSelected)
    }


    private fun getDatesBetween(startDate: LocalDate, endDate:LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate,endDate)
        return Stream.iterate(startDate){date ->
            date.plusDays( 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    private fun toUiModel(dateList:List<LocalDate>, lastSelectedDate:LocalDate): CalenderUiModel {
        return CalenderUiModel(
            selectedDate = toItemUiModel(lastSelectedDate,true, latitude = 0.0, longitude = 0.0),
            visibleDates = dateList.map {
                toItemUiModel(it,it.isEqual(lastSelectedDate), longitude = 0.0, latitude = 0.0)
            }
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate:Boolean,longitude: Double,latitude: Double) = CalenderUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
        longitude = longitude,
        latitude = latitude

    )
}
