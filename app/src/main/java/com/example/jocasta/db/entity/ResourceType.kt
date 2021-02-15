package com.example.jocasta.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "resource_types")
data class ResourceType(
    @PrimaryKey(autoGenerate = true)
    val resourceID : Int = 0,

    val resourceName : String,

    val resourceAPIURL : String
) : Parcelable
