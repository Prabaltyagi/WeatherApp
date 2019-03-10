package com.prabal.weatherapp.di.components

import com.prabal.weatherapp.di.modules.NetModule
import com.prabal.weatherapp.views.weather_details.WeatherViewModel
import dagger.Component
import javax.inject.Singleton
/**
 * Component to provide inject methods.
 */
@Singleton
@Component(modules = arrayOf((NetModule::class)))
interface ViewModelInjector {
    fun inject(weatherViewModel: WeatherViewModel)
}