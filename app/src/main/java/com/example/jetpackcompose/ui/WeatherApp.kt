package com.example.jetpackcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.jetpackcompose.viewmodel.WeatherViewModel
import com.example.jetpackcompose.ui.components.BottomNavBar
import com.example.jetpackcompose.ui.views.CurrentWeatherView
import com.example.jetpackcompose.ui.views.ForecastWeatherView
import com.example.jetpackcompose.ui.views.SettingsView

/**
 * Main Composable for the Weather App.
 *
 * This function manages the app's primary interface, including current weather,
 * weather forecasts, and settings. It dynamically switches between these views
 * based on the selection in the bottom navigation bar.
 *
 * @param viewModel The [WeatherViewModel] that provides weather data.
 */
@Composable
fun WeatherApp(viewModel: WeatherViewModel) {
    // States for weather data and the currently selected navigation item
    val currentWeather by viewModel.currentWeather.collectAsState()
    val forecast by viewModel.forecast.collectAsState()
    val iconUrl by viewModel.iconUrl.collectAsState()

    // State for the currently selected bottom navigation item
    var selectedItem by remember { mutableStateOf(0) }

    // Colors for the upper and lower halves of the app
    val upperHalfColor = Color.White
    val lowerHalfColor = Color(0xFF1E88E5)

    // Main layout of the app
    Box(modifier = Modifier.fillMaxSize()) {
        // Background colors for the upper and lower halves
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(upperHalfColor)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(lowerHalfColor)
            )
        }

        // Content of the app
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(upperHalfColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Display based on the selected navigation option
                when (selectedItem) {
                    0 -> CurrentWeatherView(currentWeather = currentWeather, iconUrl = iconUrl)
                    1 -> ForecastWeatherView(forecast = forecast)
                    2 -> SettingsView(onSave = { selectedItem = 0 })
                }
            }

            // Bottom navigation bar at the bottom of the app
            BottomNavBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it },
                modifier = Modifier.align(Alignment.BottomCenter),
                backgroundColor = lowerHalfColor
            )
        }
    }
}
