package com.lablabla.blablawatering.data.local

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class WateringTypeConverters {

    private val moshi = Moshi.Builder().build()
    private val membersType = Types.newParameterizedType(List::class.java, ZoneEntity::class.java)
    private val membersAdapter = moshi.adapter<List<ZoneEntity>>(membersType)

    @TypeConverter
    fun fromListToString(list: List<ZoneEntity>): String {
        return membersAdapter.toJson(list)
    }

    @TypeConverter
    fun toZoneEntity(dataString: String?): List<ZoneEntity> {
        if(dataString == null || dataString.isEmpty()) {
            return mutableListOf()
        }
        return membersAdapter.fromJson(dataString).orEmpty()
    }
}