package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.local.ZoneEntity
import com.lablabla.blablawatering.data.mapper.toZone
import com.lablabla.blablawatering.data.mapper.toZoneEntity
import com.lablabla.blablawatering.domain.model.Zone
import com.lablabla.blablawatering.domain.repository.JSONHandler
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class JSONHandlerMoshiImpl: JSONHandler {

    private val moshi: Moshi = Moshi.Builder().build()
    private var zonesListType = Types.newParameterizedType(
        MutableList::class.java,
        ZoneEntity::class.java
    )
    private val zonesAdapter: JsonAdapter<List<ZoneEntity>> = moshi.adapter(zonesListType)

    override fun parseZones(jsonString: String): List<Zone>? {
        val moshiList = zonesAdapter.fromJson(jsonString)
        return moshiList?.map {
            it.toZone()
        }
    }

    override fun stringifyZones(zones: List<Zone>): String {
        return zonesAdapter.toJson(
            zones.map {
                it.toZoneEntity()
            })
    }
}