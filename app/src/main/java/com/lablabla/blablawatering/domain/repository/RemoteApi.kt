package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Station
import com.lablabla.blablawatering.domain.model.WateringEvent

interface RemoteApi {

    suspend fun getStations(): List<Station>

    suspend fun setStations(stations: List<Station>)

    suspend fun getWateringEvents(): List<WateringEvent>

    suspend fun setWateringEvents(wateringEvents: List<WateringEvent>)
}