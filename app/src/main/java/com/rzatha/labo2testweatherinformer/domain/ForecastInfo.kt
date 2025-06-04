package com.rzatha.labo2testweatherinformer.domain

data class ForecastInfo(
    val city: String,
    val firstDayAverageTemp: AverageTempDay,
    val secondDayAverageTemp: AverageTempDay,
    val thirdDayAverageTemp: AverageTempDay,
    val fourthDayAverageTemp: AverageTempDay,
    val fifthDayAverageTemp: AverageTempDay,
)
