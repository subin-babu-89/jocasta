package com.example.jocasta.model

import com.squareup.moshi.Json

class PeopleResult(
    @property:Json(name = "count")
    var count: Int? = null,

    @property:Json(name = "next")
    var next: String? = null,

    @property:Json(name = "previous")
    var previous: String? = null,

    @property:Json(name = "results")
    var people: List<People>? = null
)
