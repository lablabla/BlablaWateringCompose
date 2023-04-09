package com.lablabla.blablawatering.domain.repository

import com.lablabla.blablawatering.domain.model.Zone

interface JSONHandler {
    fun parseZones(jsonString: String) : List<Zone>?

    fun stringifyZones(zones: List<Zone>): String

    // TODO: Add parseEvents
}