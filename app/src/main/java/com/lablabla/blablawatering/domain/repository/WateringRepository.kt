package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Station
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.util.Resource
import kotlinx.coroutines.flow.Flow

interface WateringRepository {

    suspend fun getStations(getFromRemote: Boolean): Flow<Resource<List<Station>>>

    suspend fun setStations(setInRemote: Boolean, stations: List<Station>): Flow<Resource<Void>>

    suspend fun getWateringEvents(getFromRemote: Boolean): Flow<Resource<List<WateringEvent>>>

    suspend fun setWateringEvents(setInRemote: Boolean, wateringEvent: List<WateringEvent>): Flow<Resource<Boolean>>
}