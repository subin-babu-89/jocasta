package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Starship

/**
 * Room DAO for starship resource type in the app
 */
@Dao
interface StarshipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Starship>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(species: Starship)

    @Query("SELECT * FROM starship WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Starship>

    @Query("SELECT * FROM starship WHERE url LIKE :url")
    suspend fun elementByURl(url: String): List<Starship>

    @Query("DELETE FROM starship")
    suspend fun clearAll()
}