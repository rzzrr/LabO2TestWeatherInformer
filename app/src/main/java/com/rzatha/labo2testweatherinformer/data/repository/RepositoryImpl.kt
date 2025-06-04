package com.rzatha.labo2testweatherinformer.data.repository

import android.util.Log
import com.rzatha.labo2testweatherinformer.data.mapper.Mapper
import com.rzatha.labo2testweatherinformer.data.network.dto.ForecastResponse
import com.rzatha.labo2testweatherinformer.data.network.retrofit.WeatherApiFactory
import com.rzatha.labo2testweatherinformer.domain.ForecastInfo
import com.rzatha.labo2testweatherinformer.domain.repository.Repository

class RepositoryImpl : Repository {

    private val apiService = WeatherApiFactory.weatherApiService
    private val mapper = Mapper()

    override suspend fun getCurrentWeather(lat: Double, lon: Double) =
        mapper.mapWeatherResponseToWeatherInfo(apiService.getCurrentWeather(lat, lon))


    override suspend fun getWeeklyForecast(lat: Double, lon: Double) : ForecastInfo {
        val forecast = apiService.getWeeklyForecast(lat, lon)
        Log.d("forecast", forecast.city.name.toString())
        val mappped = mapper.mapForecastResponseToForecastInfo(forecast)
        return mappped
    }


}