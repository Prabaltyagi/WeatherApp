package com.prabal.weatherapp.views.weather_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prabal.weatherapp.R
import com.prabal.weatherapp.base.BaseViewModel
import com.prabal.weatherapp.model.WeatherData
import com.prabal.weatherapp.model.data.WeatherDataSource
import com.prabal.weatherapp.model.data.WeatherRepository
import com.prabal.weatherapp.network.WeatherApi
import com.prabal.weatherapp.utils.Event
import javax.inject.Inject

/**
 * View model sub class to process weather api data
 */
class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    @Inject
    lateinit var weatherApi: WeatherApi

  //oneway data binding
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean>
        get() =_dataLoading
    private val _addingLocation = MutableLiveData<Boolean>()
    val addingLocation: LiveData<Boolean>
        get() = _addingLocation

    private val _weatherData = MutableLiveData<WeatherData>()
    private val weatherData: LiveData<WeatherData>
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

    //bidiractional data binding// should not private
     val latitude = MutableLiveData<String>()

    //bidiractional data binding //should not private
     val longitude = MutableLiveData<String>()


    private val _newLocationEvent = MutableLiveData<Event<String>>()
    val newLocationEvent: LiveData<Event<String>>
        get() = _newLocationEvent

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarMessage: LiveData<Event<Int>>
        get() = _snackbarText



    /*start Load weather details data
    * */
    fun startLoad(){
        visibleAddLocation()
        loadWeatherData(true)
    }

    /*Load weather details data
        * */
    private fun loadWeatherData( showLoadingUI: Boolean) {
        if (showLoadingUI) {
            //start loading view
            _dataLoading.setValue(true)
        }

        weatherRepository.getWeatherData(latitude.value?:"",longitude.value?:"",2,weatherApi,object :WeatherDataSource.GetWeatherDataCallback{

            override fun onWeatherDataLoaded(weather: WeatherData) {

                if (showLoadingUI) {
                    //stop loading ui
                    _dataLoading.value = false
                }
                _weatherData.value=weather
                setup()
            }

            override fun onDataNotAvailable() {
            }
        })


    }

    // on/off UI to add latitude and longitude of location
    fun visibleAddLocation(){
        if( _addingLocation.value?:false){
            _addingLocation.value=false
        }else{
            _addingLocation.value=true
        }

    }

    //save the location detail
     fun saveLocation() {
        val latitue = latitude.value
        val longitude = longitude.value

        if (latitue == null || longitude == null) {
            _snackbarText.value =  Event(R.string.message_blank_location)
            return
        }else{
            addNewLocaion(latitue+","+longitude)
        }

    }

    /*notify to save the location and start load data
    * */
    fun addNewLocaion(location: String) {
        _newLocationEvent.value = Event(location)
       // var lo:String=newLocationEvent.value?:""
        val separate1 = location.split(",".toRegex())
        var lat:String=separate1.get(0)
        var lon:String=separate1.get(1)
        latitude.value=lat
        longitude.value=lon
        // start loading data
        startLoad()

    }

    /*
    setup recieve data value
    * */
    fun setup(){
        _minTemp.value =getCelsiusTemp( weatherData.value?.listWeather?.get(0)?.temp?.minTemp ?: 0.0).toInt()
        _maxTemp.value = getCelsiusTemp(weatherData.value?.listWeather?.get(0)?.temp?.maxTemp ?: 0.0).toInt()
        _cityName.value=weatherData.value?.city?.cityName ?:"Empty city name"
     //clear lat long at view
      /*  latitude.value=""
        longitude.value=""*/
    }

    /*convert kelvin to celsius
    param:temprature(Double)
    * */
    fun getCelsiusTemp(temp:Double):Double{
       // Log.d("Temp:","is "+temp-273.15)
        return temp-273.15;

    }


}