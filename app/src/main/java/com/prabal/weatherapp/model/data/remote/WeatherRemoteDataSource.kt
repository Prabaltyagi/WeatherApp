package com.prabal.weatherapp.model.data.remote

import com.prabal.weatherapp.model.data.WeatherDataSource

object WeatherRemoteDataSource: WeatherDataSource {
    override fun getWeatherData(
        lat: String,
        long: String,
        dayCount: Int,
        callback: WeatherDataSource.GetWeatherDataCallback
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //implement network call
    }
}