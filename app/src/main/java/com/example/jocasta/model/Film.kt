package com.example.jocasta.model

import com.squareup.moshi.Json

data class Film(
    @property:Json(name = "title")
    var title: String? = null,

    @property:Json(name = "episode_id")
    var episodeId: Int? = null,

    @property:Json(name = "opening_crawl")
    var openingCrawl: String? = null,

    @property:Json(name = "director")
    var director: String? = null,

    @property:Json(name = "producer")
    var producer: String? = null,

    @property:Json(name = "release_date")
    var releaseDate: String? = null,

    @property:Json(name = "characters")
    var characters: List<String>? = null,

    @property:Json(name = "planets")
    var planets: List<String>? = null,

    @property:Json(name = "starships")
    var starships: List<String>? = null,

    @property:Json(name = "vehicles")
    var vehicles: List<String>? = null,

    @property:Json(name = "species")
    var species: List<String>? = null,

    @property:Json(name = "created")
    var created: String? = null,

    @property:Json(name = "edited")
    var edited: String? = null,

    @property:Json(name = "url")
    var url: String? = null
)