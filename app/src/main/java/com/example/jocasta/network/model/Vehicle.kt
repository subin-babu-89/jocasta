package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "vehicle")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    var peopleID : Long = 0L,

    @Json(name = "name")
    var name: String? = null,

    @Json(name = "model")
    var model: String? = null,

    @Json(name = "manufacturer")
    var manufacturer: String? = null,

    @Json(name = "cost_in_credits")
    var costInCredits: String? = null,

    @Json(name = "length")
    var length: String? = null,

    @Json(name = "max_atmosphering_speed")
    var maxAtmospheringSpeed: String? = null,

    @Json(name = "crew")
    var crew: String? = null,

    @Json(name = "passengers")
    var passengers: String? = null,

    @Json(name = "cargo_capacity")
    var cargoCapacity: String? = null,

    @Json(name = "consumables")
    var consumables: String? = null,

    @Json(name = "vehicle_class")
    var vehicleClass: String? = null,

    @Json(name = "pilots")
    var pilots: List<String>? = null,

    @Json(name = "films")
    var films: List<String>? = null,

    @Json(name = "created")
    var created: String? = null,

    @Json(name = "edited")
    var edited: String? = null,

    @Json(name = "url")
    override var url: String
): Parcelable, AbstractResource()
