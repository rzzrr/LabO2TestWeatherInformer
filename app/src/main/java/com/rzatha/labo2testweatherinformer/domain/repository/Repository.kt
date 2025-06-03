package com.rzatha.labo2testweatherinformer.domain.repository

import com.rzatha.labo2testweatherinformer.domain.WeatherInfo

interface Repository {

    suspend fun getCurrentWeather(lat: Double, lon: Double) : WeatherInfo

    suspend fun getWeeklyForecast()
}