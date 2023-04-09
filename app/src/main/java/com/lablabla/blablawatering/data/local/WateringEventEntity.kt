package com.lablabla.blablawatering.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WateringEventEntity(
    val name: String,
    val cron: String,
    val zones: List<ZoneEntity>,
    @PrimaryKey val id: Int? = null
)
