package com.example.jocasta.repository

import androidx.paging.*
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.AbstractResource
import com.example.jocasta.network.model.People
import com.example.jocasta.network.model.Planet
import com.example.jocasta.repository.mediator.FilmRemoteMediator
import com.example.jocasta.repository.mediator.PeopleRemoteMediator
import com.example.jocasta.repository.mediator.PlanetRemoteMediator
import com.example.jocasta.repository.mediator.SpeciesRemoteMediator
import kotlinx.coroutines.flow.Flow

@Suppress("UNCHECKED_CAST")
class ResourceRepository (private val service : SWApiService, private val database: JocastaDatabase) {

    companion object {
        const val NETWORK_PAGE_SIZE = 5
    }

    @OptIn(ExperimentalPagingApi::class)
    fun <T : AbstractResource> getSearchResults(resourceType: String, query : String) : Flow<PagingData<T>>{
        val dbQuery = "%${query.replace(' ', '%')}%"

        val pagingSourceFactory = getPagingSourceFactory(resourceType, dbQuery) as () -> PagingSource<Int, T>

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = getRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, T>,
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
            "films" -> {
                { database.filmDao().elementsByName(dbQuery)} as () -> PagingSource<Int, AbstractResource>
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
            "films" -> {
                FilmRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            "species" -> {
                SpeciesRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, AbstractResource>
             }
            else -> {
                PeopleRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
        }
    }
}