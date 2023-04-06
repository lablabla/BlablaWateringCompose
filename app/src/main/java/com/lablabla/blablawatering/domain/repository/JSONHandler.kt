package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Station

interface JSONHandler {
    fun parseStations(jsonString: String) : List<Station>?

    fun stringifyStations(stations: List<Station>): String

    // TODO: Add parseEvents
}