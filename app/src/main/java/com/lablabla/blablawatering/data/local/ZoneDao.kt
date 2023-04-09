package com.lablabla.blablawatering.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ZoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZones(
        zoneEntities: List<ZoneEntity>
    )

    @Query("SELECT * FROM zoneentity")
    suspend fun getZones(): List<ZoneEntity>

    @Query("DELETE FROM zoneentity")
    suspend fun clearZones()
}