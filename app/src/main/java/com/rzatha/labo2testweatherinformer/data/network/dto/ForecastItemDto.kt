package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastItemDto(
    @SerializedName("main") val weatherTempDto: WeatherTempDto,
    @SerializedName("weather") val weather: List<WeatherItemDto>,
    @SerializedName("dt_txt") val dateAsString: String
)
