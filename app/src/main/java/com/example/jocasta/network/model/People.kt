package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "people")
@Parcelize
data class People(
    @PrimaryKey(autoGenerate = true)
    var peopleID: Long = 0L,

    @Json(name = "name")
    override var name: String? = "",

    @Json(name = "height")
    var height: String = "",

    @Json(name = "mass")
    var mass: String = "",

    @Json(name = "hair_color")
    var hairColor: String = "",

    @Json(name = "skin_color")
    var skinColor: String = "",

    @Json(name = "eye_color")
    var eyeColor: String = "",

    @Json(name = "birth_year")
    var birthYear: String = "",

    @Json(name = "gender")
    var gender: String = "",

    @Json(name = "homeworld")
    var homeworld: String = "",

    @Json(name = "films")
    var films: List<String> = listOf(),

    @Json(name = "species")
    var species: List<String> = listOf(),

    @Json(name = "vehicles")
    var vehicles: List<String> = listOf(),

    @Json(name = "starships")
    var starships: List<String> = listOf(),

    @Json(name = "created")
    var created: String = "",

    @Json(name = "edited")
    var edited: String = "",

    @Json(name = "url")
    override var url: String = "",

    override var title: String? = ""
) : Parcelable, AbstractResource()