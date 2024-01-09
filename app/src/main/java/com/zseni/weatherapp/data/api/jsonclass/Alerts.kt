package com.zseni.weatherapp.data.api.jsonclass

import com.google.gson.annotations.SerializedName

data class Alerts(
    @SerializedName("sender_name")
    val senderName:String,
    val event:String,
    val start:Long,
    val end:Long,
    val description:String,
    val tags:Long
)
