package com.prabal.weatherapp.network

import com.prabal.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("forecast/daily")
    fun dailyForecast(@Query("lat") lat : String,
                      @Query("lan") long : String,
                      @Query("cnt") dayCount : Int) : Call<WeatherData>
}