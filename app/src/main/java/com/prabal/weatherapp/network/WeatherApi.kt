package com.prabal.weatherapp.network

import com.prabal.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * All the Api for weather
 * */
interface WeatherApi {

    /**
     * get the forcast details based on lattitude, langitude with days  count
     * */
    @GET("forecast")
    fun getDailyForecast(@Query("lat") lat : String,
                      @Query("lon") longi : String,
                      @Query("cnt") dayCount : Int) : Call<WeatherData>
}