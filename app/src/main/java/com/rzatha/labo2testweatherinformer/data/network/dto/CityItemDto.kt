package com.rzatha.labo2testweatherinformer.data.network.dto

import com.google.gson.annotations.SerializedName

data class CityItemDto(
    @SerializedName("coord") val coord : CoordDto,
    @SerializedName("name") val name : String
)
