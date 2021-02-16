package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "planets")
@Parcelize
data class Planet(
    @PrimaryKey(autoGenerate = true)
    var peopleID : Long = 0L,

    @property:Json(name = "name")
    override var name: String? = "",

    @property:Json(name = "rotation_period")
    var rotationPeriod: String = "",

    @property:Json(name = "orbital_period")
    var orbitalPeriod: String = "",

    @property:Json(name = "diameter")
    var diameter: String = "",

    @property:Json(name = "climate")
    var climate: String = "",

    @property:Json(name = "gravity")
    var gravity: String = "",

    @property:Json(name = "terrain")
    var terrain: String = "",

    @property:Json(name = "surface_water")
    var surfaceWater: String = "",

    @property:Json(name = "population")
    var population: String = "",

    @property:Json(name = "residents")
    var residents: List<String> = listOf(),

    @property:Json(name = "films")
    var films: List<String> = listOf(),

    @property:Json(name = "created")
    var created: String = "",

    @property:Json(name = "edited")
    var edited: String = "",

    @property:Json(name = "url")
    override var url: String = "",

    override var title: String? = ""
) : Parcelable, AbstractResource()
