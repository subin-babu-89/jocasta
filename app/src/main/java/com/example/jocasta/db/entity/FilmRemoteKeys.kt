package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table) for film dao remote keys
 */
@Entity(tableName = "remote_keys_film")
data class FilmRemoteKeys(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
