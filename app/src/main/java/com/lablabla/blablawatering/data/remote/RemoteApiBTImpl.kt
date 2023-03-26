package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.domain.model.Station
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.domain.repository.BluetoothService
import com.lablabla.blablawatering.domain.repository.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteApiBTImpl @Inject constructor(
    bluetoothService: BluetoothService
): RemoteApi {
    private var stations: MutableList<Station> = mutableListOf()

    private var wateringEvents: List<WateringEvent> = emptyList()

    init {
//        bluetoothService.scanBleDevices()
    }

    override suspend fun getStations(): List<Station> {
        return stations
    }

    override suspend fun setStations(stations: List<Station>) {
        this.stations = stations as MutableList<Station>
    }

    override suspend fun getWateringEvents(): List<WateringEvent> {
        return wateringEvents
    }

    override suspend fun setWateringEvents(wateringEvents: List<WateringEvent>) {
        this.wateringEvents = wateringEvents
    }
}