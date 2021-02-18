package com.example.jocasta.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity (table) for people dao remote keys
 */
@Entity(tableName = "remote_keys")
data class PeopleRemoteKeys(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
