package com.rzatha.labo2testweatherinformer.domain.repository

import com.rzatha.labo2testweatherinformer.data.network.dto.ForecastResponse
import com.rzatha.labo2testweatherinformer.domain.CurrentWeatherInfo
import com.rzatha.labo2testweatherinformer.domain.ForecastInfo

interface Repository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherInfo

    suspend fun getWeeklyForecast(lat: Double, lon: Double): ForecastInfo
}