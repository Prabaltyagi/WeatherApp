package com.prabal.weatherapp.di.components

import com.prabal.weatherapp.di.modules.NetModule
import com.prabal.weatherapp.views.weather_details.WeatherViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Component to provide inject methods.
 */

@Singleton
@Component(modules = [(NetModule::class)])
interface ViewModelInjector {

    @Component.Builder
    interface Builder {

       /* @BindsInstance
        fun appModule(appModule: AppModule): Builder*/
       @BindsInstance
        fun netModule(netModule: NetModule): Builder


        fun build(): ViewModelInjector
    }


    fun inject(weatherViewModel: WeatherViewModel)
}