package com.zseni.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp, bottomEnd = 60.dp, bottomStart = 60.dp),
    medium = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 60.dp, bottomStart = 60.dp),
    large = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomEnd = 30.dp, bottomStart = 30.dp)
)

val BottomShape = Shapes(medium = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 40.dp, bottomStart = 40.dp))
val SearchShape = Shapes(medium = RoundedCornerShape(12.dp))