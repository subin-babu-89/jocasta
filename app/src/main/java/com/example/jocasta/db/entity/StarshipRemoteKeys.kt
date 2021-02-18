package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table) for starship dao remote keys
 */
@Entity(tableName = "remote_keys_starship")
data class StarshipRemoteKeys(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
