package com.rzatha.labo2testweatherinformer.domain.usecase

import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class GetWeeklyForecastUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(lat: Double, lon: Double) =
        repository.getWeeklyForecast(lat, lon)

}