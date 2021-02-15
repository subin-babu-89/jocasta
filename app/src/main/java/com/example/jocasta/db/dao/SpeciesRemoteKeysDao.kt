package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.FilmRemoteKeys
import com.example.jocasta.db.entity.PlanetRemoteKeys
import com.example.jocasta.db.entity.SpeciesRemoteKeys

@Dao
interface SpeciesRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remotePeopleKey: List<SpeciesRemoteKeys>)

    @Query("SELECT * FROM remote_keys_species WHERE id = :id")
    suspend fun remoteKeysForPerson(id: Long): SpeciesRemoteKeys?

    @Query("DELETE FROM remote_keys_species")
    suspend fun clearRemoteKeys()
}