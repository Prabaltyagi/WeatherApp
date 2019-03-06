package com.prabal.weatherapp.model.data

import com.prabal.weatherapp.model.WeatherData

class WeatherRepository (
    val weatherRemoteDataSource: WeatherDataSource
) : WeatherDataSource {


    override fun getWeatherData(
        lat: String,
        long: String,
        dayCount: Int,
        callback: WeatherDataSource.GetWeatherDataCallback
    ) {
       //call from repo
        weatherRemoteDataSource.getWeatherData(lat,long,dayCount,object : WeatherDataSource.GetWeatherDataCallback{
            override fun onWeatherDataLoaded(weather: WeatherData) {
                callback.onWeatherDataLoaded(weather)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })

    }

    companion object {

        private var INSTANCE: WeatherRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.

         * @param weatherRemoteDataSource the backend data source
         * *
         * *
         * @return the [WeatherRepository] instance
         */
        @JvmStatic fun getInstance(weatherRemoteDataSource: WeatherDataSource
                                  ) =
            INSTANCE ?: synchronized(WeatherRepository::class.java) {
                INSTANCE ?: WeatherRepository(weatherRemoteDataSource)
                    .also { INSTANCE = it }
            }

        /**
         * force  to create a new instance nexttime
         */
        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }
    }






