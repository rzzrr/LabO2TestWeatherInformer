package com.rzatha.labo2testweatherinformer.data.mapper

import com.rzatha.labo2testweatherinformer.data.network.dto.ForecastResponse
import com.rzatha.labo2testweatherinformer.data.network.dto.WeatherResponse
import com.rzatha.labo2testweatherinformer.domain.AverageTempDay
import com.rzatha.labo2testweatherinformer.domain.CurrentWeatherInfo
import com.rzatha.labo2testweatherinformer.domain.ForecastInfo

class Mapper {
    fun mapWeatherResponseToWeatherInfo(response: WeatherResponse) =
        CurrentWeatherInfo(
            response.coord.lon,
            response.coord.lat,
            response.city,
            response.weatherTemp.temp,
            response.weather.first().clarity,
            response.weather.first().description
        )

    fun mapForecastResponseToForecastInfo(response: ForecastResponse): ForecastInfo {
        val city = response.city.name
        val chunkedAverageTempList = response.forecastItemsList.chunked(8).map {
            val date = it.first().dateAsString

            val averageTemp = it.map {
                it.weatherTempDto.temp
            }.average()
            AverageTempDay(date, averageTemp)
        }
        return ForecastInfo(
            city,
            chunkedAverageTempList[0],
            chunkedAverageTempList[1],
            chunkedAverageTempList[2],
            chunkedAverageTempList[3],
            chunkedAverageTempList[4],
        )
    }
}