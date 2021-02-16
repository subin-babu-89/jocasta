package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.VehicleRemoteKeys

@Dao
interface VehicleRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remotePeopleKey: List<VehicleRemoteKeys>)

    @Query("SELECT * FROM remote_keys_vehicle WHERE id = :id")
    suspend fun remoteKeysForPerson(id: Long): VehicleRemoteKeys?

    @Query("DELETE FROM remote_keys_vehicle")
    suspend fun clearRemoteKeys()
}