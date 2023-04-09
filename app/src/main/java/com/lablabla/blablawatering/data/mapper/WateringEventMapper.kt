package com.lablabla.blablawatering.data.mapper

import com.lablabla.blablawatering.data.local.WateringEventEntity
import com.lablabla.blablawatering.domain.model.WateringEvent

fun WateringEventEntity.toWateringEvent(): WateringEvent {
    return WateringEvent(
        name = name,
        cron = cron,
        zones = zones.map { it.toZone() }
    )
}

fun WateringEvent.toWateringEventEntity(): WateringEventEntity {
    return WateringEventEntity(
        name = name,
        cron = cron,
        zones = zones.map { it.toZoneEntity() }
    )
}