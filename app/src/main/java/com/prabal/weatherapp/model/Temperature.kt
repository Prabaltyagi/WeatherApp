package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName

/**
 * A  data model class which holds the Temperature details  data
 * */
data class Temperature (@SerializedName("humidity") var humidity: Double,
                        @SerializedName("temp") var temp: Double,
                        @SerializedName("temp_min") var minTemp: Double,
                        @SerializedName("temp_max") var maxTemp: Double

)