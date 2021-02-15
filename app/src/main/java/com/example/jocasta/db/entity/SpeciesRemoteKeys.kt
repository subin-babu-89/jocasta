package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys_species")
data class SpeciesRemoteKeys(
    @PrimaryKey
    val id : Long,
    val prevKey : Int?,
    val nextKey: Int?
)
