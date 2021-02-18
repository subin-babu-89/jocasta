package com.example.jocasta.repository

import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.toDBDao

/**
 * Repository for the initial list of the resource fetched from swapi
 *
 */
class ResourceListRepository(
    private val service: SWApiService,
    private val database: JocastaDatabase
) {

    /**
     * Fetch resources from DB. If not present, sync from network and then return the value
     */
    suspend fun getResourcesList(): List<ResourceType> {
        if (database.resourceTypeDao().allResourceTypes().isNullOrEmpty()) {
            val allResources = service.getAllResources()
            database.resourceTypeDao().insertAll(allResources.toDBDao())
        }
        return database.resourceTypeDao().allResourceTypes()
    }
}