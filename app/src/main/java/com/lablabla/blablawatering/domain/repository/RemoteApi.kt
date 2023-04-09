package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.model.WateringEvent

interface RemoteApi {

    suspend fun getZones(): List<Zone>

    suspend fun setZones(zones: List<Zone>)

    suspend fun getWateringEvents(): List<WateringEvent>

    suspend fun setWateringEvents(wateringEvents: List<WateringEvent>)
}