package com.rzatha.labo2testweatherinformer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzatha.labo2testweatherinformer.data.repository.RepositoryImpl
import com.rzatha.labo2testweatherinformer.domain.CurrentWeatherInfo
import com.rzatha.labo2testweatherinformer.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val _Current_weatherInfo = MutableLiveData<CurrentWeatherInfo>()
    lateinit var currentWeatherInfo: CurrentWeatherInfo
    val weatherInfo: LiveData<CurrentWeatherInfo>
        get() = _Current_weatherInfo

    fun getCurrentWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            val weatherInfoResponse = GetCurrentWeatherUseCase(repository).invoke(lat, lon)
            _Current_weatherInfo.postValue(weatherInfoResponse)
        }
    }
}