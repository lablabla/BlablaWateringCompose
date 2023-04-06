package com.lablabla.blablawatering.data.repository

import com.lablabla.blablawatering.data.local.StationsMoshi
import com.lablabla.blablawatering.data.mapper.toStation
import com.lablabla.blablawatering.domain.model.Station
import com.lablabla.blablawatering.domain.repository.JSONHandler
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class JSONHandlerMoshiImpl: JSONHandler {

    private val moshi: Moshi = Moshi.Builder().build()
    private var stationsListType = Types.newParameterizedType(
        MutableList::class.java,
        StationsMoshi::class.java
    )
    private val stationsAdapter: JsonAdapter<List<StationsMoshi>> = moshi.adapter(stationsListType)

    override fun parseStations(jsonString: String): List<Station>? {
        val moshiList = stationsAdapter.fromJson(jsonString)
        return moshiList?.map {
            it.toStation()
        }
    }

    override fun stringifyStations(stations: List<Station>): String {
        TODO("Not yet implemented")
    }
}