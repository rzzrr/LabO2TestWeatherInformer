package com.rzatha.labo2testweatherinformer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzatha.labo2testweatherinformer.data.repository.RepositoryImpl
import com.rzatha.labo2testweatherinformer.domain.WeatherInfo
import com.rzatha.labo2testweatherinformer.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val _weatherInfo = MutableLiveData<WeatherInfo>()

    val weatherInfo : LiveData<WeatherInfo>
        get() = _weatherInfo

    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            val weatherInfoResponse = GetCurrentWeatherUseCase(repository).invoke(lat, lon)
            _weatherInfo.postValue(weatherInfoResponse)
        }
    }
}