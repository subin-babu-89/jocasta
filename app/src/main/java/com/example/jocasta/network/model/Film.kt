package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    var peopleID : Long = 0L,

    @Json(name = "title")
    override var title: String?,

    @Json(name = "episode_id")
    var episodeId: Int,

    @Json(name = "opening_crawl")
    var openingCrawl: String,

    @Json(name = "director")
    var director: String,

    @Json(name = "producer")
    var producer: String,

    @Json(name = "release_date")
    var releaseDate: String,

    @Json(name = "characters")
    var characters: List<String>,

    @Json(name = "planets")
    var planets: List<String>,

    @Json(name = "starships")
    var starships: List<String>,

    @Json(name = "vehicles")
    var vehicles: List<String>,

    @Json(name = "species")
    var species: List<String>,

    @Json(name = "created")
    var created: String,

    @Json(name = "edited")
    var edited: String,

    @Json(name = "url")
    override var url: String,

    override var name: String?
) : Parcelable, AbstractResource()
