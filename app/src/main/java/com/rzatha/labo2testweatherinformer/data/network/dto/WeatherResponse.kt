package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") val coord: CoordDto,
    @SerializedName("weather") val weather: List<WeatherItemDto>,
    @SerializedName("main") val weatherTemp: WeatherTempDto,
    @SerializedName("name") val city: String
)

