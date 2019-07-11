package com.prabal.weatherapp.model

import com.google.gson.annotations.SerializedName
import com.prabal.weatherapp.constants.COUNTRY
import com.prabal.weatherapp.constants.NAME
/**
 * A city data model class which holds the city details
* */
data class City(@SerializedName(NAME) var cityName : String,
                @SerializedName(COUNTRY) var country : String)