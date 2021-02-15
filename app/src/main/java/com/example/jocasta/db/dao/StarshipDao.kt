package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.Planet
import com.example.jocasta.network.model.Starship

@Dao
interface StarshipDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people : List<Starship>)

    @Query("SELECT * FROM starship WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Starship>

    @Query("DELETE FROM starship")
    suspend fun clearAll()
}