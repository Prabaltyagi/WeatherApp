package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName
import com.prabal.weatherapp.constants.Data_RECIEVING_TIME
import com.prabal.weatherapp.constants.MAIN
import com.prabal.weatherapp.constants.WEATHER

/**
 * A  data model class which holds the weather details list data
 * */
 data class ListWeather (
    @SerializedName(Data_RECIEVING_TIME)
    var dt: String,

    @SerializedName(MAIN)
    var temp: Temperature,

    @SerializedName(WEATHER)
    var weatherDetails: Array<WeatherDetails>? = null

   )



