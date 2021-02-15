package com.example.jocasta.network.model

import com.squareup.moshi.Json

data class Results<T> (
    @Json(name = "count")
    var count: Int? = null,

    @Json(name = "next")
    var next: String? = null,

    @Json(name = "previous")
    var previous: String? = null,

    @Json(name = "results")
    var elements: List<T>? = null
)
