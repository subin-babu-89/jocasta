package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.Planet
import com.example.jocasta.network.model.Species

@Dao
interface SpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people : List<Species>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(species: Species)

    @Query("SELECT * FROM species WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Species>

    @Query("SELECT * FROM species WHERE url LIKE :url")
    suspend fun elementByURl(url : String) : List<Species>

    @Query("DELETE FROM species")
    suspend fun clearAll()
}