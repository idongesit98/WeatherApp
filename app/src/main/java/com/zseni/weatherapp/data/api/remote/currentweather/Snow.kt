package com.zseni.weatherapp.data.api.remote.currentweather

import com.google.gson.annotations.SerializedName

data class Snow(
    @SerializedName("3h")
    val `3h`: Double
)