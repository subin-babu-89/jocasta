package com.example.jocasta.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.SpeciesRemoteKeys
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Species
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class SpeciesRemoteMediator(
    private val resourceType : String,
    private val query: String,
    private val service: SWApiService,
    private val database: JocastaDatabase
) : RemoteMediator<Int, Species>(){
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Species>): MediatorResult {
        val page = when(loadType){
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1)?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                if (remoteKeys == null)
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                val prevKey = remoteKeys.prevKey
                if(prevKey == null){
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastTime(state)
                if (remoteKeys == null || remoteKeys.nextKey == null)
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                remoteKeys.nextKey
            }
        }

        try {
            val apiResponse = service.getSpeciesSearchFor(resourceType, query, page)
            val elements = apiResponse.elements!!
            var endOfPaginationReached = elements.isEmpty() || apiResponse.next == null

            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.speciesRemoteKeysDao().clearRemoteKeys()
                    database.speciesDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = elements.map {
                    SpeciesRemoteKeys(id = it.getNumber(), prevKey = prevKey, nextKey = nextKey)
                }
                database.speciesRemoteKeysDao().insertAll(keys)
                database.speciesDao().insertAll(elements)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch (exception : IOException){
            return MediatorResult.Error(exception)
        }catch (exception : HttpException){
            return MediatorResult.Error(exception)
        }
    }



    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Species>): SpeciesRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { species ->
                database.speciesRemoteKeysDao().remoteKeysForPerson(species.getNumber())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Species>): SpeciesRemoteKeys? {
        return state.pages.firstOrNull(){ it.data.isNotEmpty() }?.data?.firstOrNull()?.let { species ->
            database.speciesRemoteKeysDao().remoteKeysForPerson(species.getNumber())
        }
    }

    private suspend fun getRemoteKeyForLastTime(state: PagingState<Int, Species>): SpeciesRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { species ->
            database.speciesRemoteKeysDao().remoteKeysForPerson(species.getNumber())
        }
    }
}

fun Species.getNumber() : Long {
    return this.url.split("/")[5].toLong()
}