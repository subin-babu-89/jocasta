package com.example.jocasta.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jocasta.db.entity.ResourceType

@Dao
interface ResourceTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(resourceTypes : List<ResourceType>)

    @Query("SELECT * FROM resource_types")
    suspend fun allResourceTypes() : List<ResourceType>

    @Query("DELETE FROM resource_types")
    suspend fun clearAll()
}