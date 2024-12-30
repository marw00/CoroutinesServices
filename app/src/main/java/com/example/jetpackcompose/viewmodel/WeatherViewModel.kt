package com.example.jetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.api.WeatherApiService
import com.example.jetpackcompose.data.ForecastItem
import com.example.jetpackcompose.data.WeatherData
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel responsible for handling weather data, including the current weather,
 * weather forecast, and related UI states.
 */
class WeatherViewModel : ViewModel() {

    // StateFlow for current weather data
    private val _currentWeather = MutableStateFlow<WeatherData?>(null)
    val currentWeather: StateFlow<WeatherData?> = _currentWeather

    // StateFlow for weather forecast data
    private val _forecast = MutableStateFlow<List<ForecastItem>>(emptyList())
    val forecast: StateFlow<List<ForecastItem>> = _forecast

    // StateFlow for weather icon URL
    private val _iconUrl = MutableStateFlow<String?>(null)
    val iconUrl: StateFlow<String?> get() = _iconUrl

    // StateFlow for error messages
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    /**
     * Fetches the current weather data for a given city using the specified API key.
     *
     * @param city The name of the city to fetch weather data for.
     * @param apiKey The API key required for accessing the weather service.
     */
    fun fetchWeatherData(city: String, apiKey: String) {
        viewModelScope.launch {
            if (apiKey.isBlank() || city.isBlank()) {
                _errorMessage.value = "API key or city name are invalid."
                return@launch
            }
            try {
                val weatherResponse = WeatherApiService.fetchWeather(city, apiKey)
                if (weatherResponse != null) {
                    _currentWeather.value = weatherResponse
                    fetchWeatherIcon(weatherResponse.weather.firstOrNull()?.icon.orEmpty())
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Failed to fetch weather. Please check your API key or city name."
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.localizedMessage}"
            }
        }
    }

    /**
     * Fetches the weather forecast data for a given city using the specified API key.
     *
     * @param city The name of the city to fetch the forecast for.
     * @param apiKey The API key required for accessing the forecast service.
     */
    fun fetchForecastData(city: String, apiKey: String) {
        viewModelScope.launch {
            if (apiKey.isBlank() || city.isBlank()) {
                _errorMessage.value = "API key or city name are invalid."
                return@launch
            }
            try {
                val forecastResponse = WeatherApiService.fetchForecast(city, apiKey)
                if (forecastResponse != null && forecastResponse.list.isNotEmpty()) {
                    _forecast.value = forecastResponse.list
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "Failed to fetch forecast. Please check your API key or city name."
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.localizedMessage}"
            }
        }
    }

    /**
     * Generates the URL for fetching the weather icon based on the icon ID.
     *
     * @param iconId The icon ID provided in the weather data.
     */
    private fun fetchWeatherIcon(iconId: String) {
        if (iconId.isNotEmpty()) {
            _iconUrl.value = "https://openweathermap.org/img/wn/$iconId@2x.png"
        }
    }
}
