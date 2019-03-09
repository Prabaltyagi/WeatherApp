package com.prabal.weatherapp.views.weather_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prabal.weatherapp.base.BaseViewModel
import com.prabal.weatherapp.model.WeatherData
import com.prabal.weatherapp.model.data.WeatherDataSource
import com.prabal.weatherapp.model.data.WeatherRepository
import com.prabal.weatherapp.network.WeatherApi
import javax.inject.Inject


class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    @Inject
    lateinit var weatherApi: WeatherApi

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() = _dataLoading

    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData>
        get() = _weatherData

    private val _minTemp = MutableLiveData<Int>()
    val minTemp: LiveData<Int>
        get() = _minTemp

    private val _maxTemp = MutableLiveData<Int>()
    val maxTemp: LiveData<Int>
        get() = _maxTemp

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String>
        get() = _cityName

   /* _maxTemp.value=0.0
    _minTemp.value=0.0

    _cityName.value="New Delhi"*/

   /* fun bind(weatherData: WeatherData){
        minTemp.value = weatherData.listWeather?.get(0)?.temp?.minTemp;
        maxTemp.value = weatherData.listWeather?.get(0)?.temp?.maxTemp;
    }
*/

    public fun startLoad(){
        loadWeatherData(true)
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the [TasksDataSource]
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private fun loadWeatherData( showLoadingUI: Boolean) {
        if (showLoadingUI) {
            _dataLoading.setValue(true)
        }

        weatherRepository.getWeatherData("28.644800","77.216721",2,weatherApi,object :WeatherDataSource.GetWeatherDataCallback{

            override fun onWeatherDataLoaded(weather: WeatherData) {

                if (showLoadingUI) {
                    _dataLoading.value = false
                }
                _weatherData.value=weather
                setup()
            }

            override fun onDataNotAvailable() {
            }
        })


    }

    fun setup(){


        _minTemp.value =getCelsiusTemp( weatherData.value?.listWeather?.get(0)?.temp?.minTemp ?: 0.0).toInt()
        _maxTemp.value = getCelsiusTemp(weatherData.value?.listWeather?.get(0)?.temp?.maxTemp ?: 0.0).toInt()
        _cityName.value=weatherData.value?.city?.cityName ?:"Empty city name"
    }
    fun getCelsiusTemp(temp:Double):Double{
        return temp-273.15;

    }
}