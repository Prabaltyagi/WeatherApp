package com.prabal.weatherapp.base

import androidx.lifecycle.ViewModel
import com.prabal.weatherapp.di.components.DaggerViewModelInjector
import com.prabal.weatherapp.di.components.ViewModelInjector
import com.prabal.weatherapp.di.modules.NetModule
import com.prabal.weatherapp.views.weather_details.WeatherViewModel

/**
 * base ViewModel to keep dependencies for all viewmodels .
 */
abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .netModule(NetModule)
            .build()

    /**
     * Init first for dependencies injection
     */
    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            //add multipal dependencies if require
            is WeatherViewModel -> injector.inject(this)
        }
    }
}