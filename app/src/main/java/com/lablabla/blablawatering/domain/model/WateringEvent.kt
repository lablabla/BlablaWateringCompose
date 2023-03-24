package com.lablabla.blablawatering.domain.model

data class WateringEvent(
    val name: String,
    val cron: String,
    val stations: List<Station>,
)
