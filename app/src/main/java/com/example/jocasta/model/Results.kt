package com.example.jocasta.model

import com.squareup.moshi.Json

data class Results<T> (
    @property:Json(name = "count")
    var count: Int? = null,

    @property:Json(name = "next")
    var next: String? = null,

    @property:Json(name = "previous")
    var previous: String? = null,

    @property:Json(name = "results")
    var people: List<T>? = null
)
