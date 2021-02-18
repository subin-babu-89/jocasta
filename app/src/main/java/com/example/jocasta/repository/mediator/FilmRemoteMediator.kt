package com.example.jocasta.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.FilmRemoteKeys
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Film
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val resourceType: String,
    private val query: String,
    private val service: SWApiService,
    private val database: JocastaDatabase
) : RemoteMediator<Int, Film>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Film>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastTime(state)
                if (remoteKeys?.nextKey == null)
                    throw InvalidObjectException("Remote key and the prevKey should not be null")
                remoteKeys.nextKey
            }
        }

        try {
            val apiResponse = service.getFilmSearchFor(resourceType, query, page)
            val elements = apiResponse.elements!!
            val endOfPaginationReached = elements.isEmpty() || apiResponse.next == null

            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.filmRemoteKeysDao().clearRemoteKeys()
                    database.filmDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = elements.map {
                    FilmRemoteKeys(id = it.getNumber(), prevKey = prevKey, nextKey = nextKey)
                }
                database.filmRemoteKeysDao().insertAll(keys)
                database.filmDao().insertAll(elements)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { film ->
                database.filmRemoteKeysDao().remoteKeysForPerson(film.getNumber())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { film ->
                database.filmRemoteKeysDao().remoteKeysForPerson(film.getNumber())
            }
    }

    private suspend fun getRemoteKeyForLastTime(state: PagingState<Int, Film>): FilmRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { film ->
            database.filmRemoteKeysDao().remoteKeysForPerson(film.getNumber())
        }
    }

}

fun Film.getNumber(): Long {
    return this.url.split("/")[5].toLong()
}