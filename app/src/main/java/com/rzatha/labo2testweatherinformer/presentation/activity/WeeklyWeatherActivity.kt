package com.rzatha.labo2testweatherinformer.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rzatha.labo2testweatherinformer.R

class WeeklyWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_weather)
    }

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, WeeklyWeatherActivity::class.java)
        }
    }
}