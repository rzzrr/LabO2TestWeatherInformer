package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherTempDto (
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feelsLike: Double
)