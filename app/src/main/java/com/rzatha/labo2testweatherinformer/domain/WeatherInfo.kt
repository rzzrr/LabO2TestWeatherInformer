package com.rzatha.labo2testweatherinformer.domain

import android.health.connect.datatypes.units.Temperature

data class WeatherInfo (
    val lon: Double,
    val lat: Double,
    val location: String,
    val temperature: Double,
    val clarity: String,
    val description: String
)