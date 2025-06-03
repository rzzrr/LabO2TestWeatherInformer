package com.rzatha.labo2testweatherinformer.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiFactory {

    private const val BASE_URL = "https://api.openweathermap.org/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val weatherApiService = retrofit.create(WeatherApiService::class.java)

}