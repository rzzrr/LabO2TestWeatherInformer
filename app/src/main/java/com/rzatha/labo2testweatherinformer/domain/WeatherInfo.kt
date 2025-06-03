package com.rzatha.labo2testweatherinformer.domain

import android.health.connect.datatypes.units.Temperature

data class WeatherInfo (
    val location: String,
    val temperature: Temperature
)