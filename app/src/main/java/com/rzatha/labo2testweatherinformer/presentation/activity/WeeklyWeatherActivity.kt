package com.rzatha.labo2testweatherinformer.presentation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rzatha.labo2testweatherinformer.R
import com.rzatha.labo2testweatherinformer.databinding.ActivityWeeklyWeatherBinding
import com.rzatha.labo2testweatherinformer.domain.CurrentWeatherInfo
import com.rzatha.labo2testweatherinformer.domain.ForecastInfo
import com.rzatha.labo2testweatherinformer.presentation.formatDate
import com.rzatha.labo2testweatherinformer.presentation.viewmodel.WeeklyWeatherViewModel
import com.rzatha.labo2testweatherinformer.presentation.withCelsius
import kotlin.math.roundToInt

class WeeklyWeatherActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWeeklyWeatherBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[WeeklyWeatherViewModel::class.java]
    }

    private val currentWeatherInfo by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(WEATHER_INFO_KEY, CurrentWeatherInfo::class.java)
                ?: throw IllegalArgumentException("Не передан WeatherInfo")
        } else {
            intent.getSerializableExtra(WEATHER_INFO_KEY) as CurrentWeatherInfo
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.forecastResponse.observe(this) {
            updateWeeklyInfo(it)
        }

        viewModel.getWeeklyForecast(lat = currentWeatherInfo.lat, lon = currentWeatherInfo.lon)
    }


    @SuppressLint("SetTextI18n")
    private fun updateWeeklyInfo(forecast: ForecastInfo) {

        with(binding) {
            tvTitle.text =
                "${resources.getString(R.string.city_average_temp_title)} ${forecast.city}"

            weatherDay1.tvAvgTemp.text =
                forecast.firstDayAverageTemp.averageTemp.roundToInt().toString().withCelsius()
            weatherDay1.tvDate.text = forecast.firstDayAverageTemp.date.formatDate()
            weatherDay2.tvAvgTemp.text =
                forecast.secondDayAverageTemp.averageTemp.roundToInt().toString().withCelsius()
            weatherDay2.tvDate.text = forecast.secondDayAverageTemp.date.formatDate()
            weatherDay3.tvAvgTemp.text =
                forecast.thirdDayAverageTemp.averageTemp.roundToInt().toString().withCelsius()
            weatherDay3.tvDate.text = forecast.thirdDayAverageTemp.date.formatDate()
            weatherDay4.tvAvgTemp.text =
                forecast.fourthDayAverageTemp.averageTemp.roundToInt().toString().withCelsius()
            weatherDay4.tvDate.text = forecast.fourthDayAverageTemp.date.formatDate()
            weatherDay5.tvAvgTemp.text =
                forecast.fifthDayAverageTemp.averageTemp.roundToInt().toString().withCelsius()
            weatherDay5.tvDate.text = forecast.fifthDayAverageTemp.date.formatDate()
        }
    }

    companion object {
        private const val WEATHER_INFO_KEY = "weather_info"
        fun newIntent(context: Context, currentWeatherInfo: CurrentWeatherInfo) =
            Intent(context, WeeklyWeatherActivity::class.java).apply {
                putExtra(WEATHER_INFO_KEY, currentWeatherInfo)
            }

    }
}