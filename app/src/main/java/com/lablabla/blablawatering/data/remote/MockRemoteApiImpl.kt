package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.domain.repository.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockRemoteApiImpl @Inject constructor() : RemoteApi {
    private var zones: MutableList<Zone> = mutableListOf()

    private var wateringEvents: List<WateringEvent> = emptyList()

    init {
        zones.add(Zone(0, "Test zone 1", 1, false))
        zones.add(Zone(1, "Test zone 2", 2, false))
        zones.add(Zone(2, "Test zone 3", 3, false))
    }

    override suspend fun getZones(): List<Zone> {
        return zones
    }

    override suspend fun setZones(zones: List<Zone>) {
        this.zones = zones as MutableList<Zone>
    }

    override suspend fun getWateringEvents(): List<WateringEvent> {
        return wateringEvents
    }

    override suspend fun setWateringEvents(wateringEvents: List<WateringEvent>) {
        this.wateringEvents = wateringEvents
    }
}