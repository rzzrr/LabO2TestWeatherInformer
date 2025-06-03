package com.rzatha.labo2testweatherinformer.domain.usecase

import com.rzatha.labo2testweatherinformer.domain.WeatherInfo
import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class GetCurrentWeatherUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(lat: Double, lon: Double) : WeatherInfo {
        return repository.getCurrentWeather(lat, lon)
    }

}