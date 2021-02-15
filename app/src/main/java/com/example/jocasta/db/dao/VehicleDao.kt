package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.Planet
import com.example.jocasta.network.model.Vehicle

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people : List<Vehicle>)

    @Query("SELECT * FROM vehicle WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, Vehicle>

    @Query("DELETE FROM vehicle")
    suspend fun clearAll()
}