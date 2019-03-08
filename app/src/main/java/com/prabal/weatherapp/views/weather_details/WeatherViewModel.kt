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

    private val _minTemp = MutableLiveData<Double>()
    val minTemp: LiveData<Double>
        get() = _minTemp

    private val _maxTemp = MutableLiveData<Double>()
    val maxTemp: LiveData<Double>
        get() = _maxTemp

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String>
        get() = _cityName

   /* fun bind(weatherData: WeatherData){
        minTemp.value = weatherData.listWeather?.get(0)?.temp?.minTemp;
        maxTemp.value = weatherData.listWeather?.get(0)?.temp?.maxTemp;
    }
*/


    /**
     * @param forceUpdate   Pass in true to refresh the data in the [TasksDataSource]
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private fun loadWeatherData( showLoadingUI: Boolean) {
        if (showLoadingUI) {
            _dataLoading.setValue(true)
        }

        weatherRepository.getWeatherData("22","22",10,weatherApi,object :WeatherDataSource.GetWeatherDataCallback{

            override fun onWeatherDataLoaded(weather: WeatherData) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                if (showLoadingUI) {
                    _dataLoading.value = false
                }
                _weatherData.value=weather
                setup()
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }

    fun setup(){
        _minTemp.value = weatherData.value?.listWeather?.get(0)?.temp?.minTemp;
        _maxTemp.value = weatherData.value?.listWeather?.get(0)?.temp?.maxTemp;
        _cityName.value=weatherData.value?.city?.cityName;
    }
}