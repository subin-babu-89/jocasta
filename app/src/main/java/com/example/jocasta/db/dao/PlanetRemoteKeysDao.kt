package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.PlanetRemoteKeys

@Dao
interface PlanetRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remotePeopleKey: List<PlanetRemoteKeys>)

    @Query("SELECT * FROM remote_keys_planet WHERE id = :id")
    suspend fun remoteKeysForPerson(id: Long): PlanetRemoteKeys?

    @Query("DELETE FROM remote_keys_planet")
    suspend fun clearRemoteKeys()
}