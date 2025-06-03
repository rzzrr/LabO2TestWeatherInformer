package com.rzatha.labo2testweatherinformer.data.repository

import com.rzatha.labo2testweatherinformer.data.mapper.Mapper
import com.rzatha.labo2testweatherinformer.data.network.retrofit.WeatherApiFactory
import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class RepositoryImpl : Repository {

    private val apiService = WeatherApiFactory.weatherApiService
    private val mapper = Mapper()

    override suspend fun getCurrentWeather(lat: Double, lon: Double) =
        mapper.mapWeatherResponseToWeatherInfo(apiService.getCurrentWeather(lat, lon))


    override suspend fun getWeeklyForecast() {
        apiService.getWeeklyForecast()
    }
}