package com.lablabla.blablawatering.domain.model

data class Zone(
    val id: Int,
    val name: String,
    val gpio: Int,
    val state: Boolean = false
)
