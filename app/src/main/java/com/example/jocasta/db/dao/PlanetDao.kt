package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Planet

/**
 * Room DAO for planet resource type in the app
 */
@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Planet>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(planet: Planet)

    @Query("SELECT * FROM planets WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Planet>

    @Query("SELECT * FROM planets WHERE url LIKE :planetURL")
    suspend fun elementByUrl(planetURL: String): List<Planet>

    @Query("DELETE FROM planets")
    suspend fun clearAll()
}