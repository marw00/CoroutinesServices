package com.example.jetpackcompose.service

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.ComponentActivity

/**
 * A manager class to handle operations related to the PopupService, including
 * requesting notification permissions and starting the service.
 * @property context The context used for managing the service and permissions. This
 * must be an instance of [ComponentActivity].
 */
class PopupServiceManager(private val context: Context) {

    /**
     * Requests the POST_NOTIFICATIONS permission from the user.
     * If the permission is granted, the PopupService will be started.
     * If the permission is denied, a toast message will notify the user.
     * **Note:** This method is applicable only on Android 13 (API level 33) and above.
     */
    fun requestPermission() {
        val requestPermissionLauncher =
            (context as ComponentActivity).registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    startPopupService()
                } else {
                    Toast.makeText(
                        context,
                        "Permission denied, notifications won't work",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        // Check and request permission if running on Android 13 or above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    /**
     * Starts the PopupService as a foreground service.
     * This method assumes that the required notification permissions have already been granted.
     */
    fun startPopupService() {
        val serviceIntent = Intent(context, PopupService::class.java)
        context.startForegroundService(serviceIntent)
    }
}
