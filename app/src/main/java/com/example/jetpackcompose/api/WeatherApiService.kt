package com.example.jetpackcompose.api

import android.util.Log
import com.example.jetpackcompose.data.ForecastData
import com.example.jetpackcompose.data.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service for communicating with the OpenWeatherMap API.
 * Provides methods to fetch current weather data and weather forecasts.
 */
object WeatherApiService {

    // Base URL for the OpenWeatherMap API
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // HTTP client for API requests
    private val client = OkHttpClient.Builder().build()

    // Retrofit instance for API communication
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // API endpoints interface
    private val api = retrofit.create(WeatherApi::class.java)

    /**
     * Interface defining the OpenWeatherMap API endpoints.
     */
    interface WeatherApi {
        /**
         * Fetches the current weather data for a specific city.
         *
         * @param city The name of the city.
         * @param apiKey The API key for authentication.
         * @param units The measurement unit for the data (default is "metric").
         * @return A `Response` containing the weather data.
         */
        @GET("weather")
        suspend fun fetchWeather(
            @Query("q") city: String,
            @Query("appid") apiKey: String,
            @Query("units") units: String = "metric"
        ): retrofit2.Response<WeatherData>

        /**
         * Fetches the weather forecast for a specific city.
         *
         * @param city The name of the city.
         * @param apiKey The API key for authentication.
         * @param units The measurement unit for the data (default is "metric").
         * @return A `Response` containing the forecast data.
         */
        @GET("forecast")
        suspend fun fetchForecast(
            @Query("q") city: String,
            @Query("appid") apiKey: String,
            @Query("units") units: String = "metric"
        ): retrofit2.Response<ForecastData>
    }

    /**
     * Fetches the current weather data for a city.
     *
     * @param city The name of the city.
     * @param apiKey The API key for authentication.
     * @return The weather data, or `null` if the request failed.
     */
    suspend fun fetchWeather(city: String, apiKey: String): WeatherData? {
        return try {
            withContext(Dispatchers.Default) {
                val response = api.fetchWeather(city, apiKey)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("WeatherApiService", "Failed to fetch data: ${response.code()}")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("WeatherApiService", "Error fetching data: ${e.message}")
            null
        }
    }

    /**
     * Fetches the weather forecast for a city.
     *
     * @param city The name of the city.
     * @param apiKey The API key for authentication.
     * @return The forecast data, or `null` if the request failed.
     */
    suspend fun fetchForecast(city: String, apiKey: String): ForecastData? {
        return try {
            withContext(Dispatchers.IO) {
                val response = api.fetchForecast(city, apiKey)
                if (response.isSuccessful) {
                    response.body()
                } else {
                    Log.e("WeatherApiService", "Failed to fetch forecast: ${response.code()}")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("WeatherApiService", "Error fetching forecast: ${e.message}")
            null
        }
    }
}
