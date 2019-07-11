package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName
/**
 * A  data model class which holds the weather description for the city
 * */
data class WeatherDetails(@SerializedName("main") var main : String,
                              @SerializedName("description") var description: String,
                              @SerializedName("icon") var icon: String)