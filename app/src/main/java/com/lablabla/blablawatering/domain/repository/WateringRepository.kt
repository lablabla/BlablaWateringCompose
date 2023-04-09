package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.util.Resource
import kotlinx.coroutines.flow.Flow

interface WateringRepository {

    suspend fun getZones(getFromRemote: Boolean): Flow<Resource<List<Zone>>>

    suspend fun setZones(setInRemote: Boolean, zones: List<Zone>): Flow<Resource<Void>>

    suspend fun getWateringEvents(getFromRemote: Boolean): Flow<Resource<List<WateringEvent>>>

    suspend fun setWateringEvents(setInRemote: Boolean, wateringEvent: List<WateringEvent>): Flow<Resource<Boolean>>
}