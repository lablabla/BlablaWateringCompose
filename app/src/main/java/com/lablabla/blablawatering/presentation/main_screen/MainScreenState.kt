package com.lablabla.blablawatering.presentation.main_screen

import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.Station

data class MainScreenState(
    val device: Device? = null,
    val stations: List<Station> = listOf(),
    val isLoading: Boolean = false
)