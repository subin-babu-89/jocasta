package com.example.jocasta.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.network.model.People

@Dao
interface PeopleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people : List<People>)

    @Query("SELECT * FROM people WHERE name LIKE :queryString ")
    fun elementsByName(queryString: String): PagingSource<Int, People>

    @Query("DELETE FROM people")
    suspend fun clearAll()
}