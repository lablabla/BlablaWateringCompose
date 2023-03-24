package com.lablabla.blablawatering.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [StationEntity::class, WateringEventEntity::class],
    version = 1
)
@TypeConverters(WateringTypeConverters::class)
abstract class WateringDatabase: RoomDatabase() {
    abstract val stationsDao: StationsDao
    abstract val wateringEventsDao: WateringEventsDao
}