package com.prabal.weatherapp

import android.app.Application

class MyApp :Application (){

    private lateinit var instance: MyApp

    override fun onCreate() {
        super.onCreate()
        instance=this
    }



    override fun onTerminate() {
        super.onTerminate()
    }
}