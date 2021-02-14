package com.example.jocasta.model

import com.squareup.moshi.Json

data class People(
    @property:Json(name = "name")
    var name: String? = null,

    @property:Json(name = "height")
    var height: String? = null,

    @property:Json(name = "mass")
    var mass: String? = null,

    @property:Json(name = "hair_color")
    var hairColor: String? = null,

    @property:Json(name = "skin_color")
    var skinColor: String? = null,

    @property:Json(name = "eye_color")
    var eyeColor: String? = null,

    @property:Json(name = "birth_year")
    var birthYear: String? = null,

    @property:Json(name = "gender")
    var gender: String? = null,

    @property:Json(name = "homeworld")
    var homeworld: String? = null,

    @property:Json(name = "films")
    var films: List<String>? = null,

    @property:Json(name = "species")
    var species: List<String>? = null,

    @property:Json(name = "vehicles")
    var vehicles: List<String>? = null,

    @property:Json(name = "starships")
    var starships: List<String>? = null,

    @property:Json(name = "created")
    var created: String? = null,

    @property:Json(name = "edited")
    var edited: String? = null,

    @property:Json(name = "url")
    var url: String? = null
)