package com.example.jocasta.model

import com.squareup.moshi.Json

data class Planet(
    @property:Json(name = "name")
    var name: String? = null,

    @property:Json(name = "rotation_period")
    var rotationPeriod: String? = null,

    @property:Json(name = "orbital_period")
    var orbitalPeriod: String? = null,

    @property:Json(name = "diameter")
    var diameter: String? = null,

    @property:Json(name = "climate")
    var climate: String? = null,

    @property:Json(name = "gravity")
    var gravity: String? = null,

    @property:Json(name = "terrain")
    var terrain: String? = null,

    @property:Json(name = "surface_water")
    var surfaceWater: String? = null,

    @property:Json(name = "population")
    var population: String? = null,

    @property:Json(name = "residents")
    var residents: List<String>? = null,

    @property:Json(name = "films")
    var films: List<String>? = null,

    @property:Json(name = "created")
    var created: String? = null,

    @property:Json(name = "edited")
    var edited: String? = null,

    @property:Json(name = "url")
    var url: String? = null
)
