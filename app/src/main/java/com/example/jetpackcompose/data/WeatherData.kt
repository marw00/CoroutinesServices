package com.example.jetpackcompose.data

/**
 * Represents the overall weather data retrieved from a weather API.
 *
 * @property coord Geographical coordinates of the location.
 * @property weather List of weather conditions at the location.
 * @property base Internal parameter for API response.
 * @property main Main weather details including temperature and pressure.
 * @property visibility Visibility in meters.
 * @property wind Wind details including speed and direction.
 * @property clouds Cloudiness percentage.
 * @property dt Time of data calculation (Unix timestamp).
 * @property sys Additional system-related details like country and sunrise/sunset times.
 * @property timezone Offset in seconds from UTC.
 * @property id City ID.
 * @property name City name.
 * @property cod Response code (e.g., 200 for success).
 */
data class WeatherData(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

/**
 * Represents the geographical coordinates of a location.
 *
 * @property lon Longitude of the location.
 * @property lat Latitude of the location.
 */
data class Coord(val lon: Double, val lat: Double)

/**
 * Represents a weather condition.
 *
 * @property id Weather condition ID.
 * @property main Group of weather parameters (e.g., Rain, Snow, Clear).
 * @property description Description of the weather condition.
 * @property icon Icon ID representing the weather condition.
 */
data class Weather(val id: Int, val main: String, val description: String, val icon: String)

/**
 * Represents the main weather details.
 *
 * @property temp Current temperature in Kelvin.
 * @property feels_like Perceived temperature in Kelvin.
 * @property temp_min Minimum temperature at the moment in Kelvin.
 * @property temp_max Maximum temperature at the moment in Kelvin.
 * @property pressure Atmospheric pressure in hPa.
 * @property humidity Humidity percentage.
 */
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

/**
 * Represents wind details.
 *
 * @property speed Wind speed in meters/second.
 * @property deg Wind direction in degrees (meteorological).
 * @property gust Wind gust speed in meters/second.
 */
data class Wind(val speed: Double, val deg: Int, val gust: Double)

/**
 * Represents cloudiness details.
 *
 * @property all Cloudiness percentage (0-100).
 */
data class Clouds(val all: Int)

/**
 * Represents system-related details like country and sunrise/sunset times.
 *
 * @property type Internal parameter for system information.
 * @property id Internal parameter for system information.
 * @property country Country code (e.g., "US" for the United States).
 * @property sunrise Sunrise time (Unix timestamp).
 * @property sunset Sunset time (Unix timestamp).
 */
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
