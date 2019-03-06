package com.prabal.weatherapp.model.data

import com.prabal.weatherapp.model.WeatherData

interface WeatherDataSource {

    interface GetWeatherDataCallback {

        fun onWeatherDataLoaded(weather: WeatherData)

        fun onDataNotAvailable()
    }

    fun getWeatherData(lat: String,long:String,dayCount:Int, callback: GetWeatherDataCallback)
}