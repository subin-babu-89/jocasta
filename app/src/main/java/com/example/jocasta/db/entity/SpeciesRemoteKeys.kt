package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table) for species dao remote keys
 */
@Entity(tableName = "remote_keys_species")
data class SpeciesRemoteKeys(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
