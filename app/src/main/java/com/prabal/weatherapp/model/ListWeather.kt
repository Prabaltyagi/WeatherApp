package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName

 data class ListWeather (
    @SerializedName("dt")
    var dt: String,

    var temp: Temperature,

    @SerializedName("deg")
    var degree: String,

    @SerializedName("weather")
    var weatherDetails: Array<WeatherDetails>? = null,

    var humidity: String,

    var pressure: String,

    var clouds: String,

    var speed: String)



