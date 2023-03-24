package com.lablabla.blablawatering.data.mapper

import com.lablabla.blablawatering.data.local.StationEntity
import com.lablabla.blablawatering.domain.model.Station

fun StationEntity.toStation(): Station {
    return Station(
        id = id,
        name = name,
        gpio = gpio
    )
}

fun Station.toStationEntity(): StationEntity {
    return StationEntity(
        id = id,
        name = name,
        gpio = gpio
    )
}