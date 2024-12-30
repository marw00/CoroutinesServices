package com.example.jetpackcompose.data

/**
 * Represents the forecast data response from the OpenWeatherMap API.
 * @property cod The response code from the API.
 * @property message A message or status code from the API.
 * @property cnt The number of forecast items included in the response.
 * @property list The list of forecast details for specific timestamps.
 */
data class ForecastData(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItem>
)

/**
 * Represents a single forecast item containing weather details for a specific timestamp.
 * @property dt The timestamp for the forecast, represented as seconds since the epoch.
 * @property main The main weather data, such as temperature and pressure.
 * @property weather A list of weather conditions (e.g., rain, clouds).
 * @property clouds The cloud coverage data.
 * @property wind The wind conditions.
 * @property visibility The visibility distance in meters.
 * @property pop The probability of precipitation (values between 0 and 1).
 * @property sys Additional system information for the forecast.
 * @property dt_txt The timestamp for the forecast in a human-readable format.
 * @property rain Rain information, if available (e.g., volume in the last 3 hours).
 */
data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Double,
    val sys: ForecastSys,
    val dt_txt: String,
    val rain: Rain? = null
)

/**
 * Represents additional system information for the forecast.
 *
 * @property pod The part of the day (e.g., "d" for day, "n" for night).
 */
data class ForecastSys(val pod: String)

/**
 * Represents rain data in the forecast.
 * @property `3h` The volume of rain in the last 3 hours, measured in millimeters (optional).
 */
data class Rain(val `3h`: Double? = null)
