package com.example.jocasta.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.People
import com.example.jocasta.repository.mediator.PeopleRemoteMediator
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
}