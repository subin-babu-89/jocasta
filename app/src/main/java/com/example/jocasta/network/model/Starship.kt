package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "starship")
data class Starship(
    @PrimaryKey(autoGenerate = true)
    var peopleID : Long = 0L,

    @Json(name = "name")
    override var name: String?,

    @Json(name = "model")
    var model: String?,

    @Json(name = "manufacturer")
    var manufacturer: String?,

    @Json(name = "cost_in_credits")
    var costInCredits: String?,

    @Json(name = "length")
    var length: String?,

    @Json(name = "max_atmosphering_speed")
    var maxAtmospheringSpeed: String?,

    @Json(name = "crew")
    var crew: String?,

    @Json(name = "passengers")
    var passengers: String?,

    @Json(name = "cargo_capacity")
    var cargoCapacity: String?,

    @Json(name = "consumables")
    var consumables: String?,

    @Json(name = "hyperdrive_rating")
    var hyperdriveRating: String?,

    @Json(name = "MGLT")
    var mGLT: String?,

    @Json(name = "starship_class")
    var starshipClass: String?,

    @Json(name = "pilots")
    var pilots: List<String>?,

    @Json(name = "films")
    var films: List<String>?,

    @Json(name = "created")
    var created: String?,

    @Json(name = "edited")
    var edited: String?,

    @Json(name = "url")
    override var url: String,

    override var title: String?
): Parcelable, AbstractResource()
