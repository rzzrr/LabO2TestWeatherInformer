package com.rzatha.labo2testweatherinformer.domain

import java.io.Serializable


data class CurrentWeatherInfo (
    val lon: Double,
    val lat: Double,
    val location: String,
    val temperature: Double,
    val clarity: String,
    val description: String,
    val iconId: String
) : Serializable