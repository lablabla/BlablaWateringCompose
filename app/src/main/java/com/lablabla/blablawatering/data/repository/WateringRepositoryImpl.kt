package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.local.WateringDatabase
import com.lablabla.blablawatering.data.mapper.toStation
import com.lablabla.blablawatering.data.mapper.toStationEntity
import com.lablabla.blablawatering.data.mapper.toWateringEvent
import com.lablabla.blablawatering.data.mapper.toWateringEventEntity
import com.lablabla.blablawatering.domain.model.Station
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

    private val stationsDao = db.stationsDao

    private val wateringEventsDao = db.wateringEventsDao

    override suspend fun getStations(getFromRemote: Boolean): Flow<Resource<List<Station>>> {
        return flow {
            emit(Resource.Loading(true))
            val localStations = stationsDao.getStations()
            emit(Resource.Success(
                data = localStations.map { it.toStation() }
            ))
            val isDbEmpty = localStations.isEmpty()
            val loadFromCache = !isDbEmpty && !getFromRemote
            if (loadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteStations = try {
                 api.getStations()
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
                null
            }

            remoteStations?.let { stations ->
                stationsDao.clearStation()
                stationsDao.insertStations(
                    stations.map { it.toStationEntity()}
                )
                emit(Resource.Success(
                    data = stationsDao.getStations().map { it.toStation() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun setStations(setInRemote: Boolean, stations: List<Station>): Flow<Resource<Void>> {
        return flow {
            emit(Resource.Loading(true))
            stationsDao.clearStation()
            stationsDao.insertStations(
                stations.map { it.toStationEntity()}
            )
            if (setInRemote) {
                try {
                    api.setStations(stations)
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

            remoteEvents?.let { stations ->
                wateringEventsDao.clearWateringEvent()
                wateringEventsDao.insertWateringEvents(
                    stations.map { it.toWateringEventEntity()}
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