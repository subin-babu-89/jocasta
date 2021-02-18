package com.example.jocasta.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Network model & Entity (table) for Film resource
 */
@Parcelize
@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true)
    var peopleID: Long = 0L,

    @Json(name = "title")
    override var title: String? = "",

    @Json(name = "episode_id")
    var episodeId: Int = 0,

    @Json(name = "opening_crawl")
    var openingCrawl: String = "",

    @Json(name = "director")
    var director: String = "",

    @Json(name = "producer")
    var producer: String = "",

    @Json(name = "release_date")
    var releaseDate: String = "",

    @Json(name = "characters")
    var characters: List<String> = listOf(),

    @Json(name = "planets")
    var planets: List<String> = listOf(),

    @Json(name = "starships")
    var starships: List<String> = listOf(),

    @Json(name = "vehicles")
    var vehicles: List<String> = listOf(),

    @Json(name = "species")
    var species: List<String> = listOf(),

    @Json(name = "created")
    var created: String = "",

    @Json(name = "edited")
    var edited: String = "",

    @Json(name = "url")
    override var url: String = "",

    override var name: String?
) : Parcelable, AbstractResource()
