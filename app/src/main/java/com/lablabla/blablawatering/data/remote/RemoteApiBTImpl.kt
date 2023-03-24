package com.lablabla.blablawatering.data.remote

import com.lablabla.blablawatering.domain.model.Station
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.domain.repository.RemoteApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteApiBTImpl @Inject constructor(): RemoteApi {
    private var stations: List<Station> = emptyList()

    private var wateringEvents: List<WateringEvent> = emptyList()

    override suspend fun getStations(): List<Station> {
        return stations
    }

    override suspend fun setStations(stations: List<Station>) {
        this.stations = stations
    }

    override suspend fun getWateringEvents(): List<WateringEvent> {
        return wateringEvents
    }

    override suspend fun setWateringEvents(wateringEvent: List<WateringEvent>) {
        this.wateringEvents = wateringEvents
    }
}