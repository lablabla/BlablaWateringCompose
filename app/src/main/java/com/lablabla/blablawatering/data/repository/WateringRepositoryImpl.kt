package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.local.WateringDatabase
import com.lablabla.blablawatering.data.mapper.toZone
import com.lablabla.blablawatering.data.mapper.toZoneEntity
import com.lablabla.blablawatering.data.mapper.toWateringEvent
import com.lablabla.blablawatering.data.mapper.toWateringEventEntity
import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.model.WateringEvent
import com.lablabla.blablawatering.domain.repository.RemoteApi
import com.lablabla.blablawatering.domain.repository.WateringRepository
import com.lablabla.blablawatering.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WateringRepositoryImpl @Inject constructor(
    private val api: RemoteApi,
    db: WateringDatabase
): WateringRepository {

    private val zonesDao = db.zoneDao

    private val wateringEventsDao = db.wateringEventsDao

    override suspend fun getZones(getFromRemote: Boolean): Flow<Resource<List<Zone>>> {
        return flow {
            emit(Resource.Loading(true))
            val localZones = zonesDao.getZones()
            emit(Resource.Success(
                data = localZones.map { it.toZone() }
            ))
            val isDbEmpty = localZones.isEmpty()
            val loadFromCache = !isDbEmpty && !getFromRemote
            if (loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteZones = try {
                 api.getZones()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }

            remoteZones?.let { zones ->
                zonesDao.clearZones()
                zonesDao.insertZones(
                    zones.map { it.toZoneEntity()}
                )
                emit(Resource.Success(
                    data = zonesDao.getZones().map { it.toZone() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun setZones(setInRemote: Boolean, zones: List<Zone>): Flow<Resource<Void>> {
        return flow {
            emit(Resource.Loading(true))
            zonesDao.clearZones()
            zonesDao.insertZones(
                zones.map { it.toZoneEntity()}
            )
            if (setInRemote) {
                try {
                    api.setZones(zones)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(e.message.toString()))
                }
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getWateringEvents(getFromRemote: Boolean): Flow<Resource<List<WateringEvent>>> {
        return flow {
            emit(Resource.Loading(true))
            val localEvents = wateringEventsDao.getWateringEvents()
            emit(Resource.Success(
                data = localEvents.map { it.toWateringEvent() }
            ))
            val isDbEmpty = localEvents.isEmpty()
            val loadFromCache = !isDbEmpty && !getFromRemote
            if (loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteEvents = try {
                api.getWateringEvents()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }

            remoteEvents?.let { zones ->
                wateringEventsDao.clearWateringEvent()
                wateringEventsDao.insertWateringEvents(
                    zones.map { it.toWateringEventEntity()}
                )
                emit(Resource.Success(
                    data = wateringEventsDao.getWateringEvents().map { it.toWateringEvent() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun setWateringEvents(setInRemote: Boolean, wateringEvent: List<WateringEvent>): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading(true))
            wateringEventsDao.clearWateringEvent()
            wateringEventsDao.insertWateringEvents(
                wateringEvent.map { it.toWateringEventEntity()}
            )
            if (setInRemote) {
                try {
                    api.setWateringEvents(wateringEvent)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(e.message.toString()))
                }
            }
            emit(Resource.Loading(false))
        }
    }

}