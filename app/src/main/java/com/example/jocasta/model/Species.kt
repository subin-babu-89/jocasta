package com.example.jocasta.model

import com.squareup.moshi.Json


data class Species(
    @property:Json(name = "name")
    var name: String? = null,

    @property:Json(name = "classification")
    var classification: String? = null,

    @property:Json(name = "designation")
    var designation: String? = null,

    @property:Json(name = "average_height")
    var averageHeight: String? = null,

    @property:Json(name = "skin_colors")
    var skinColors: String? = null,

    @property:Json(name = "hair_colors")
    var hairColors: String? = null,

    @property:Json(name = "eye_colors")
    var eyeColors: String? = null,

    @property:Json(name = "average_lifespan")
    var averageLifespan: String? = null,

    @property:Json(name = "homeworld")
    var homeworld: String? = null,

    @property:Json(name = "language")
    var language: String? = null,

    @property:Json(name = "people")
    var people: List<String>? = null,

    @property:Json(name = "films")
    var films: List<String>? = null,

    @property:Json(name = "created")
    var created: String? = null,

    @property:Json(name = "edited")
    var edited: String? = null,

    @property:Json(name = "url")
    var url: String? = null
)
