package com.zseni.weatherapp.util

sealed class Resource<T>(
    val data: T? = null,
    val message:String? = null,
    ){
    class Success<T>(data:T?): Resource<T>(data)
    class Loading<T>(val isLoading:Boolean = true):Resource<T>(null)

    class Error<T>(data: T? = null,message: String?): Resource<T>(data, message)

}
