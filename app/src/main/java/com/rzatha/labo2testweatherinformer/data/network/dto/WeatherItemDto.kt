package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherItemDto (
    @SerializedName("main") val clarity: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val iconId: String

)
