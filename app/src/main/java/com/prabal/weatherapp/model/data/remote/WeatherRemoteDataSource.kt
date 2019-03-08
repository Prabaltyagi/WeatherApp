package com.prabal.weatherapp.model.data.remote

import android.util.Log
import com.prabal.weatherapp.model.WeatherData
import com.prabal.weatherapp.model.data.WeatherDataSource
import com.prabal.weatherapp.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRemoteDataSource: WeatherDataSource {
    override fun getWeatherData(
        lat: String,
        long: String,
        dayCount: Int,
        weatherApi: WeatherApi,
        callback: WeatherDataSource.GetWeatherDataCallback
    ) {
        //implement network call
        var weatherReq: Call<WeatherData> =weatherApi.getDailyForecast(lat,long,dayCount)

        weatherReq.enqueue(object:Callback<WeatherData>{
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {

                response.body()?.let {
                    Log.d("MyApp##","Resp:"+it)
                    callback.onWeatherDataLoaded(it)
                }
            }


            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                callback.onDataNotAvailable()
                t.printStackTrace()
            }
        }

        )
    }
}