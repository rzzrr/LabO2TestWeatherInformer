package com.rzatha.labo2testweatherinformer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzatha.labo2testweatherinformer.data.network.dto.ForecastResponse
import com.rzatha.labo2testweatherinformer.data.repository.RepositoryImpl
import com.rzatha.labo2testweatherinformer.domain.ForecastInfo
import com.rzatha.labo2testweatherinformer.domain.usecase.GetWeeklyForecastUseCase
import kotlinx.coroutines.launch

class WeeklyWeatherViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    //TODO ПЕРЕПИСАТЬ С DATA на DOMAIN с маппером

    private val _forecastResponse = MutableLiveData<ForecastInfo>()
    val forecastResponse : LiveData<ForecastInfo>
        get() = _forecastResponse

    fun getWeeklyForecast(lat: Double, lon: Double){
        viewModelScope.launch {

            val response = GetWeeklyForecastUseCase(repository).invoke(lat, lon)
            _forecastResponse.postValue(response)

        }
    }
}