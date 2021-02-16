package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.StarshipRemoteKeys

@Dao
interface StarshipRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remotePeopleKey: List<StarshipRemoteKeys>)

    @Query("SELECT * FROM remote_keys_starship WHERE id = :id")
    suspend fun remoteKeysForPerson(id: Long): StarshipRemoteKeys?

    @Query("DELETE FROM remote_keys_starship")
    suspend fun clearRemoteKeys()
}