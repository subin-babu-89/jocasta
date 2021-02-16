package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "species")
data class Species(
    @PrimaryKey(autoGenerate = true)
    var peopleID: Long = 0L,

    @Json(name = "name")
    override var name: String? = "",

    @Json(name = "classification")
    var classification: String = "",

    @Json(name = "designation")
    var designation: String = "",

    @Json(name = "average_height")
    var averageHeight: String = "",

    @Json(name = "skin_colors")
    var skinColors: String = "",

    @Json(name = "hair_colors")
    var hairColors: String = "",

    @Json(name = "eye_colors")
    var eyeColors: String = "",

    @Json(name = "average_lifespan")
    var averageLifespan: String = "",

    @Json(name = "homeworld")
    var homeworld: String? = null,

    @Json(name = "language")
    var language: String = "",

    @Json(name = "people")
    var people: List<String> = listOf(),

    @Json(name = "films")
    var films: List<String> = listOf(),

    @Json(name = "created")
    var created: String = "",

    @Json(name = "edited")
    var edited: String = "",

    @Json(name = "url")
    override var url: String = "",

    override var title: String? = ""
) : Parcelable, AbstractResource()

