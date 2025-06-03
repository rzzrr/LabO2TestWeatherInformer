package com.rzatha.labo2testweatherinformer.domain.usecase

import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class GetCurrentWeatherUseCase(
    private val repository: Repository
) {

    operator fun invoke(lat: Double, lon: Double) {
        repository.getCurrentWeather(lat, lon)
    }

}