package com.lablabla.blablawatering.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(
        stationEntities: List<StationEntity>
    )

    @Query("SELECT * FROM stationentity")
    suspend fun getStations(): List<StationEntity>

    @Query("DELETE FROM stationentity")
    suspend fun clearStation()
}