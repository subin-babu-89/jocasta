package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.FilmRemoteKeys

/**
 * Room DAO for remote keys for film resource paging in the app
 */
@Dao
interface FilmRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remotePeopleKey: List<FilmRemoteKeys>)

    @Query("SELECT * FROM remote_keys_film WHERE id = :id")
    suspend fun remoteKeysForPerson(id: Long): FilmRemoteKeys?

    @Query("DELETE FROM remote_keys_film")
    suspend fun clearRemoteKeys()
}