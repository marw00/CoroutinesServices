package com.example.jetpackcompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A customizable bottom navigation bar with three navigation items.
 * It allows the user to navigate between different sections of the app.
 *
 * @param selectedItem The index of the currently selected navigation item.
 * @param onItemSelected A callback that is triggered when a navigation item is selected.
 * @param modifier Modifier to be applied to the bottom navigation bar.
 * @param backgroundColor The background color of the bottom navigation bar.
 * @param selectedTintColor The color to apply to the icon and label when the item is selected.
 * @param unselectedTintColor The color to apply to the icon and label when the item is not selected.
 */
@Composable
fun BottomNavBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    selectedTintColor: Color = Color.White,
    unselectedTintColor: Color = Color.Black
) {
    BottomNavigation(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor
    ) {
        BottomNavigationItem(
            selected = selectedItem == 0,
            onClick = { onItemSelected(0) },
            label = { Text("Home") },
            icon = { NavigationIcon(Icons.Filled.Home, selectedItem == 0, selectedTintColor, unselectedTintColor) }
        )
        BottomNavigationItem(
            selected = selectedItem == 1,
            onClick = { onItemSelected(1) },
            label = { Text("Forecast") },
            icon = { NavigationIcon(Icons.Filled.Schedule, selectedItem == 1, selectedTintColor, unselectedTintColor) }
        )
        BottomNavigationItem(
            selected = selectedItem == 2,
            onClick = { onItemSelected(2) },
            label = { Text("Settings") },
            icon = { NavigationIcon(Icons.Filled.Settings, selectedItem == 2, selectedTintColor, unselectedTintColor) }
        )
    }
}

/**
 * A helper composable to display a navigation icon with a dynamic tint color.
 * The tint color changes based on whether the icon is selected or not.
 *
 * @param icon The image vector to display as the icon.
 * @param isSelected A boolean value indicating if the icon is selected.
 * @param selectedTintColor The color to apply to the icon when it is selected.
 * @param unselectedTintColor The color to apply to the icon when it is not selected.
 */
@Composable
fun NavigationIcon(
    icon: ImageVector,
    isSelected: Boolean,
    selectedTintColor: Color,
    unselectedTintColor: Color
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) selectedTintColor else unselectedTintColor
    )
}
