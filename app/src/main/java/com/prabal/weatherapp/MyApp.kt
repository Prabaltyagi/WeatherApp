package com.prabal.weatherapp

import android.app.Application

/**
 * Application class for app lifecycle
 * */
class MyApp :Application (){

    private lateinit var instance: MyApp

    override fun onCreate() {
        super.onCreate()
        instance=this
    }


/**
 * If shortage of memory
 * */
    override fun onTerminate() {
        super.onTerminate()
    }
}