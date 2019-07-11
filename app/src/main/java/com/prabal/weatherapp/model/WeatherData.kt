package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName
/**
 * A  data model class which holds the weather list data for the city
 * */
data class WeatherData (
    @SerializedName("city")
    var city: City,

    var cnt: String,

    var cod: String,

    var message: String,

    @SerializedName("list")
    var listWeather : List<ListWeather>?=null)

