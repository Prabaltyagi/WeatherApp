package com.prabal.weatherapp.network

import com.prabal.weatherapp.constants.OPEN_WEATHER_APP_KEY
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder().addQueryParameter("appid", OPEN_WEATHER_APP_KEY)
           .build()
        return chain.proceed(chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build())
    }

}