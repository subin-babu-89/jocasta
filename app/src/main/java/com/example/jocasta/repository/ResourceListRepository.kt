package com.example.jocasta.repository

import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.toDBDao

class ResourceListRepository(private val service: SWApiService, private val database: JocastaDatabase) {

    suspend fun getResourcesList() : List<ResourceType> {
        if(database.resourceTypeDao().allResourceTypes().isNullOrEmpty()){
            val allResources = service.getAllResources()
            database.resourceTypeDao().insertAll(allResources.toDBDao())
        }
        return database.resourceTypeDao().allResourceTypes()
    }
}