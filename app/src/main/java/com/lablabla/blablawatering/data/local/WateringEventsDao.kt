package com.lablabla.blablawatering.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WateringEventsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWateringEvents(
        wateringEventEntities: List<WateringEventEntity>
    )

    @Query("SELECT * FROM wateringevententity")
    suspend fun getWateringEvents(): List<WateringEventEntity>

    @Query("DELETE FROM wateringevententity")
    suspend fun clearWateringEvent()
}