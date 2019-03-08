package com.prabal.weatherapp.base

import androidx.lifecycle.ViewModel
import com.prabal.weatherapp.di.components.ViewModelInjector
import com.prabal.weatherapp.di.modules.NetModule
import com.prabal.weatherapp.views.weather_details.WeatherViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .netModule(NetModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is WeatherViewModel -> injector.inject(this)
        }
    }
}