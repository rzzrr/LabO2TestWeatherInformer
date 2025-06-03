package com.rzatha.labo2testweatherinformer.data.mapper

import com.rzatha.labo2testweatherinformer.data.network.dto.WeatherResponse
import com.rzatha.labo2testweatherinformer.domain.WeatherInfo

class Mapper {
    fun mapWeatherResponseToWeatherInfo(response: WeatherResponse) =
        WeatherInfo(
            response.coord.lon,
            response.coord.lat,
            response.city,
            response.weatherTemp.temp,
            response.weather.first().clarity,
            response.weather.first().description
        )
}