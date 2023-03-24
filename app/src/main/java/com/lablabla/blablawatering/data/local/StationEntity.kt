package com.lablabla.blablawatering.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StationEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val gpio: Int,
)
