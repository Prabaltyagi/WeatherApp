package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Temperature (@SerializedName("day") var dayTemperature: Double,
                        @SerializedName("night") var nightTemperature: Double,
                        @SerializedName("min") var minTemp: Double,
                        @SerializedName("max") var maxTemp: Double

)