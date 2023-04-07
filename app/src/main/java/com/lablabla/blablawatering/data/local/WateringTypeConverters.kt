package com.lablabla.blablawatering.data.local

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

class WateringTypeConverters {

    private val moshi = Moshi.Builder().build()
    private val membersType = Types.newParameterizedType(List::class.java, StationEntity::class.java)
    private val membersAdapter = moshi.adapter<List<StationEntity>>(membersType)

    @TypeConverter
    fun fromListToString(list: List<StationEntity>): String {
        return membersAdapter.toJson(list)
    }

    @TypeConverter
    fun toStationEntity(dataString: String?): List<StationEntity> {
        if(dataString == null || dataString.isEmpty()) {
            return mutableListOf()
        }
        return membersAdapter.fromJson(dataString).orEmpty()
    }
}