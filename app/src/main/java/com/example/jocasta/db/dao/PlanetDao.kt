package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Planet

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people : List<Planet>)

    @Query("SELECT * FROM planets WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Planet>

    @Query("DELETE FROM planets")
    suspend fun clearAll()
}