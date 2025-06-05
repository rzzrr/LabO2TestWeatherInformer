package com.rzatha.labo2testweatherinformer.presentation.activity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rzatha.labo2testweatherinformer.R
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

    private val locationManager by lazy {
        getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private val locationListener = WeatherLocationListener()

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            startLocationUpdates()
        } else {
            Toast.makeText(this, "Разрешение на геолокацию не предоставлено", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestLocationPermission()
        observeViewModel()
        setOnClickListeners()
    }

    private fun observeViewModel() {
        with(viewModel) {
            weatherInfo.observe(this@CurrentWeatherActivity) {
                Log.d("CurrentWeatherActivity", it.toString())

                Glide.with(this@CurrentWeatherActivity)
                    .load(getCorrectWeatherIcon(it.iconId))
                    .into(binding.ivWeatherIcon)

                viewModel.currentWeatherInfo = it
                with(binding) {
                    tvCity.text = it.location
                    textViewTodayTemp.text = it.temperature.roundToInt().toString().withCelsius()
                    tvClarity.text = it.description
                }
            }
        }
    }

    private fun setOnClickListeners() {
        binding.buttonGoToWeekly.setOnClickListener {
            val weatherInfo = viewModel.currentWeatherInfo
            val intent = WeeklyWeatherActivity.newIntent(this, weatherInfo)
            startActivity(intent)
        }
    }

    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val minTime: Long = 1_000
        val minDistanceMeters = 10f

        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistanceMeters,
                locationListener,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            Toast.makeText(this, "Ошибка доступа к местоположению", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCorrectWeatherIcon(weatherCode: String) : Int {
        return when(weatherCode) {
            "01d" -> R.drawable.im01d
            "01n" -> R.drawable.im01n
            "02d" -> R.drawable.im02d
            "02n" -> R.drawable.im02n
            "03d", "03n" -> R.drawable.im03
            "04d", "04n" -> R.drawable.im04
            "09d", "09n" -> R.drawable.im09
            "010d" -> R.drawable.im10d
            "010n" -> R.drawable.im10n
            "011d", "011n" -> R.drawable.im11
            "013d", "013n" -> R.drawable.im13
            "050d", "050n" -> R.drawable.im50
            else -> R.drawable.im01d
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }

    inner class WeatherLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            val lat = location.latitude
            val lon = location.longitude

            Log.d("Location", "Новое местоположение: lat=$lat, lon=$lon")
            viewModel.getCurrentWeather(lat, lon)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

}