package com.lablabla.blablawatering.data.mapper

import com.lablabla.blablawatering.data.local.ZoneEntity
import com.lablabla.blablawatering.domain.model.Zone

fun ZoneEntity.toZone(): Zone {
    return Zone(
        id = id,
        name = name,
        gpio = gpio
    )
}

fun Zone.toZoneEntity(): ZoneEntity {
    return ZoneEntity(
        id = id,
        name = name,
        gpio = gpio
    )
}