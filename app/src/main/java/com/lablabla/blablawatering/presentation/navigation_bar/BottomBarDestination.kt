package com.lablabla.blablawatering.presentation.navigation_bar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.lablabla.blablawatering.R
import com.lablabla.blablawatering.presentation.destinations.DirectionDestination
import com.lablabla.blablawatering.presentation.destinations.EventsScreenDestination
import com.lablabla.blablawatering.presentation.destinations.MainScreenDestination
import com.lablabla.blablawatering.presentation.destinations.StationsScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestination,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(MainScreenDestination, Icons.Default.Home, R.string.home),
    Stations(StationsScreenDestination, Icons.Default.Settings, R.string.stations),
    Events(EventsScreenDestination, Icons.Default.Schedule, R.string.events)
}
