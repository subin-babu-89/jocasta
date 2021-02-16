package com.example.jocasta.network.model

import com.example.jocasta.db.entity.ResourceType
import com.squareup.moshi.Json


data class Resources(
    @Json(name = "people")
    var people: String,

    @Json(name = "planets")
    var planets: String,

    @Json(name = "films")
    var films: String,

    @Json(name = "species")
    var species: String,

    @Json(name = "vehicles")
    var vehicles: String,

    @Json(name = "starships")
    var starships: String,
)

fun Resources.toDBDao(): List<ResourceType> {
    val resourcesList = mutableListOf<ResourceType>()
    resourcesList.add(ResourceType(resourceName = "people", resourceAPIURL = this.people))
    resourcesList.add(ResourceType(resourceName = "planets", resourceAPIURL = this.planets))
    resourcesList.add(ResourceType(resourceName = "films", resourceAPIURL = this.films))
    resourcesList.add(ResourceType(resourceName = "species", resourceAPIURL = this.species))
    resourcesList.add(ResourceType(resourceName = "vehicles", resourceAPIURL = this.vehicles))
    resourcesList.add(ResourceType(resourceName = "starships", resourceAPIURL = this.starships))
    return resourcesList
}