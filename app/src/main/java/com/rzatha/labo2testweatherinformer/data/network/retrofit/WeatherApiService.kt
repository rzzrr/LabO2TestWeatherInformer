package com.rzatha.labo2testweatherinformer.data.network.retrofit

import com.rzatha.labo2testweatherinformer.data.network.dto.WeatherResponse
import com.rzatha.labo2testweatherinformer.data.network.dto.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("/data/2.5/weather?")
    suspend fun getCurrentWeather(
        @Query(PARAM_LAT) lat: Double,
        @Query(PARAM_LON) lon: Double,
        @Query(PARAM_UNITS) units: String = DEFAULT_UNIT_KEY,
        @Query(PARAM_LANG) lang: String = DEFAULT_LANG_KEY,
        @Query(APP_ID) apiid: String = API_KEY
    ): WeatherResponse

    @GET("/data/2.5/forecast?")
    suspend fun getWeeklyForecast(
        @Query(PARAM_LAT) lat: Double,
        @Query(PARAM_LON) lon: Double,
        @Query(PARAM_UNITS) units: String = DEFAULT_UNIT_KEY,
        @Query(PARAM_LANG) lang: String = DEFAULT_LANG_KEY,
        @Query(APP_ID) apiid: String = API_KEY
    ): ForecastResponse

    companion object {
        private const val PARAM_LAT = "lat"
        private const val PARAM_LON = "lon"
        private const val PARAM_LANG = "lang"
        private const val PARAM_UNITS = "units"
        private const val APP_ID = "appid"
        private const val API_KEY = "0eaad5c680c2d7473df3c2e2ae45a624"
        private const val DEFAULT_LANG_KEY = "ru"
        private const val DEFAULT_UNIT_KEY = "metric"
    }
}