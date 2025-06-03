package com.rzatha.labo2testweatherinformer.domain.repository

interface Repository {

    fun getCurrentWeather(lat: Double, lon: Double)

    fun getWeeklyForecast()
}