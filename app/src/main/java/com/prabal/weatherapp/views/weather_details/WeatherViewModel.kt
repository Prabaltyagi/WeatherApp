package com.prabal.weatherapp.views.weather_details

import androidx.lifecycle.MutableLiveData
import com.prabal.weatherapp.base.BaseViewModel
import com.prabal.weatherapp.model.WeatherData


class WeatherViewModel: BaseViewModel() {
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