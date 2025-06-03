package com.rzatha.labo2testweatherinformer.domain.usecase

import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class GetWeeklyForecastUseCase(
    private val repository: Repository
) {

    operator fun invoke() {
        repository.getWeeklyForecast()
    }
}