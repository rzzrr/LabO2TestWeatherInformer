package com.rzatha.labo2testweatherinformer.presentation

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun String.withCelsius() = "$this°C"

fun String.formatDate(): String {
 val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
 val outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

 val date = LocalDateTime.parse(this, inputFormatter)
 return outputFormatter.format(date)
}