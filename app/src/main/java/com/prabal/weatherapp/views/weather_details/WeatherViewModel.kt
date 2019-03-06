package com.prabal.weatherapp.views.weather_details

import androidx.lifecycle.MutableLiveData
import com.prabal.weatherapp.base.BaseViewModel
import com.prabal.weatherapp.model.WeatherData
import com.prabal.weatherapp.model.data.WeatherRepository


class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {
    /*@Inject
    lateinit var retrofit: Retrofit*/
    private val minTemp = MutableLiveData<Double>()
    private val maxTemp = MutableLiveData<Double>()

    fun bind(weatherData: WeatherData){
        minTemp.value = weatherData.listWeather?.get(0)?.temp?.minTemp;
        maxTemp.value = weatherData.listWeather?.get(0)?.temp?.maxTemp;
    }

    fun getMaxTemp():MutableLiveData<Double>{
        return maxTemp
    }

    fun getMinTemp():MutableLiveData<Double>{
        return minTemp
    }
}