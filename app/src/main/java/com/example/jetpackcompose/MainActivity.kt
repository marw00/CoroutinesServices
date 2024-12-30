package com.example.jetpackcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcompose.viewmodel.WeatherViewModel
import com.example.jetpackcompose.ui.WeatherApp
import com.example.jetpackcompose.service.PopupServiceManager

/**
 * This activity sets up the Jetpack Compose content, initializes the weather view model,
 * and manages the popup service based on the Android version.
 */
class MainActivity : ComponentActivity() {
    private val popupServiceManager = PopupServiceManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Handle popup service based on the Android version
        handlePopupService()

        // Set the Jetpack Compose content
        setContent {
            val viewModel: WeatherViewModel = viewModel()
            WeatherApp(viewModel)
        }
    }

    private fun handlePopupService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            popupServiceManager.requestPermission()
        } else {
            popupServiceManager.startPopupService()
        }
    }
}
