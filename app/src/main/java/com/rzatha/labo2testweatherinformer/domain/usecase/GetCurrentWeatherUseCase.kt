package com.rzatha.labo2testweatherinformer.domain.usecase

import com.rzatha.labo2testweatherinformer.domain.CurrentWeatherInfo
import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class GetCurrentWeatherUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(lat: Double, lon: Double) : CurrentWeatherInfo {
        return repository.getCurrentWeather(lat, lon)
    }

}