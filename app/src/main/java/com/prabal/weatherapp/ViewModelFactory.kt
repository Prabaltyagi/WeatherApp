package com.prabal.weatherapp


import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabal.weatherapp.model.data.WeatherRepository
import com.prabal.weatherapp.model.data.remote.WeatherRemoteDataSource
import com.prabal.weatherapp.views.weather_details.WeatherViewModel

class ViewModelFactory private constructor(
        private val weatherRepository: WeatherRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(WeatherViewModel::class.java) ->
                        WeatherViewModel(weatherRepository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    WeatherRepository.getInstance(WeatherRemoteDataSource))
                    .also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}