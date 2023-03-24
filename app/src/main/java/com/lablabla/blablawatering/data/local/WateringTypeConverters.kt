package com.lablabla.blablawatering.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WateringTypeConverters {
    @TypeConverter
    fun fromListToString(list: List<*>): String {
        val type = object: TypeToken<List<*>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toStationEntity(dataString: String?): List<StationEntity> {
        if(dataString == null || dataString.isEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<StationEntity>>() {}.type
        return Gson().fromJson(dataString, type)
    }
}