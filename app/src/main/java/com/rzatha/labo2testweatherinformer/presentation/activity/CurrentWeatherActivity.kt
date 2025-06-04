package com.rzatha.labo2testweatherinformer.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rzatha.labo2testweatherinformer.databinding.ActivityCurrentWeatherBinding
import com.rzatha.labo2testweatherinformer.presentation.viewmodel.CurrentWeatherViewModel
import com.rzatha.labo2testweatherinformer.presentation.withCelsius
import kotlin.math.roundToInt

class CurrentWeatherActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[CurrentWeatherViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCurrentWeatherBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getCurrentWeather(lat = 45.0448, lon = 38.976)

        viewModel.weatherInfo.observe(this) {
            Log.d("CurrentWeatherActivity", it.toString())
            viewModel.currentWeatherInfo = it
            with(binding) {
                tvCity.text = it.location
                textViewTodayTemp.text = it.temperature.roundToInt().toString().withCelsius()
                tvClarity.text = it.description
            }
        }

        binding.buttonGoToWeekly.setOnClickListener {
            val weatherInfo = viewModel.currentWeatherInfo
            val intent = WeeklyWeatherActivity.newIntent(this, weatherInfo)
            startActivity(intent)
        }


    }


}