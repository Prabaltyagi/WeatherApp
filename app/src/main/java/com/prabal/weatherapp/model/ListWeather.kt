package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName

 data class ListWeather (
    @SerializedName("dt")
    var dt: String,

    @SerializedName("main")
    var temp: Temperature,

    @SerializedName("weather")
    var weatherDetails: Array<WeatherDetails>? = null

   )



