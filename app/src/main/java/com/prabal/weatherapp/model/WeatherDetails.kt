package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherDetails(@SerializedName("main") var main : String,
                              @SerializedName("description") var description: String,
                              @SerializedName("icon") var icon: String)