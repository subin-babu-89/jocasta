package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table) for planet dao remote keys
 */
@Entity(tableName = "remote_keys_planet")
data class PlanetRemoteKeys(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
