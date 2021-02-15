package com.example.jocasta.repository

import androidx.paging.*
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.AbstractResource
import com.example.jocasta.network.model.People
import com.example.jocasta.network.model.Planet
import com.example.jocasta.repository.mediator.PeopleRemoteMediator
import com.example.jocasta.repository.mediator.PlanetRemoteMediator
import kotlinx.coroutines.flow.Flow

class ResourceRepository (private val service : SWApiService, private val database: JocastaDatabase) {

    companion object {
        const val NETWORK_PAGE_SIZE = 5
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultsPeople(query : String) : Flow<PagingData<People>>{
        val dbQuery = "%${query.replace(' ', '%')}%"

        val pagingSourceFactory = { database.peopleDao().elementsByName(dbQuery) }

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PeopleRemoteMediator (
                        query,
                        service,
                        database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    fun <T : AbstractResource> getSearchResults(resourceType: String, query : String) : Flow<PagingData<T>>{
        val dbQuery = "%${query.replace(' ', '%')}%"

        val pagingSourceFactory = getPagingSourceFactory("planets", dbQuery) as () -> PagingSource<Int, T>

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = getRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, T>,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultsPlanets(query : String) : Flow<PagingData<Planet>>{
        val dbQuery = "%${query.replace(' ', '%')}%"

        val pagingSourceFactory = getPagingSourceFactory("planets", dbQuery) as () -> PagingSource<Int, Planet>

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PlanetRemoteMediator (
                query,
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    private fun getPagingSourceFactory(resourceType : String, dbQuery : String) : () -> PagingSource<Int, AbstractResource> {
        return when(resourceType){
            "people" ->{
                { database.peopleDao().elementsByName(dbQuery) }  as () -> PagingSource<Int, AbstractResource>
            }
            "planets" -> {
                { database.planetDao().elementsByName(dbQuery)}  as () -> PagingSource<Int, AbstractResource>
            }
            else -> {
                { database.peopleDao().elementsByName(dbQuery)} as () -> PagingSource<Int, AbstractResource>
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getRemoteMediator(resourceType: String, query: String, service: SWApiService, database: JocastaDatabase) : RemoteMediator<Int, AbstractResource> {
        return when(resourceType){
            "people" ->{
                PeopleRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            "planets" -> {
                PlanetRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            else -> {
                PeopleRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
        }
    }
}