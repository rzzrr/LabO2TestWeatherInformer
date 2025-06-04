package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("list") val forecastItemsList: List<ForecastItemDto>,
    @SerializedName("city") val city: CityItemDto
)