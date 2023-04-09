package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.domain.model.RemoteCommands
import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.domain.repository.BluetoothService
import com.lablabla.blablawatering.domain.repository.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteApiBTImpl @Inject constructor(
    private val bluetoothService: BluetoothService
): RemoteApi {
    private var zones: MutableList<Zone> = mutableListOf()

    private var wateringEvents: List<WateringEvent> = emptyList()

    override suspend fun getZones(): List<Zone> {
        bluetoothService.sendCommand(RemoteCommands.GetZones)
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