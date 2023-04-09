package com.lablabla.blablawatering.presentation.main_screen

import com.lablabla.blablawatering.domain.model.Device
import com.lablabla.blablawatering.domain.model.Zone
import kotlinx.serialization.Serializable

@Serializable
data class MainScreenState(
    val device: Device? = null,
    val zones: List<Zone> = listOf(),
    val isLoading: Boolean = false
)