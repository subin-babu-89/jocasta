package com.example.jocasta.model

import com.squareup.moshi.Json


class Vehicle(
    @property:Json(name = "name")
    var name: String? = null,

    @property:Json(name = "model")
    var model: String? = null,

    @property:Json(name = "manufacturer")
    var manufacturer: String? = null,

    @property:Json(name = "cost_in_credits")
    var costInCredits: String? = null,

    @property:Json(name = "length")
    var length: String? = null,

    @property:Json(name = "max_atmosphering_speed")
    var maxAtmospheringSpeed: String? = null,

    @property:Json(name = "crew")
    var crew: String? = null,

    @property:Json(name = "passengers")
    var passengers: String? = null,

    @property:Json(name = "cargo_capacity")
    var cargoCapacity: String? = null,

    @property:Json(name = "consumables")
    var consumables: String? = null,

    @property:Json(name = "vehicle_class")
    var vehicleClass: String? = null,

    @property:Json(name = "pilots")
    var pilots: List<String>? = null,

    @property:Json(name = "films")
    var films: List<String>? = null,

    @property:Json(name = "created")
    var created: String? = null,

    @property:Json(name = "edited")
    var edited: String? = null,

    @property:Json(name = "url")
    var url: String? = null
)
