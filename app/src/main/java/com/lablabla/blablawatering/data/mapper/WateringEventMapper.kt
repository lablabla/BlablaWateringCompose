package com.lablabla.blablawatering.data.mapper

import com.lablabla.blablawatering.data.local.WateringEventEntity
import com.lablabla.blablawatering.domain.model.WateringEvent

fun WateringEventEntity.toWateringEvent(): WateringEvent {
    return WateringEvent(
        name = name,
        cron = cron,
        stations = stations.map { it.toStation() }
    )
}

fun WateringEvent.toWateringEventEntity(): WateringEventEntity {
    return WateringEventEntity(
        name = name,
        cron = cron,
        stations = stations.map { it.toStationEntity() }
    )
}