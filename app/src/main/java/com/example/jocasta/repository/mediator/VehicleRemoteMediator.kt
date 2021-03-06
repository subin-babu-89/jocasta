package com.example.jocasta.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.VehicleRemoteKeys
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Vehicle
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

private const val STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class VehicleRemoteMediator(
    private val resourceType: String,
    private val query: String,
    private val service: SWApiService,
    private val database: JocastaDatabase
) : RemoteMediator<Int, Vehicle>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Vehicle>
    ): MediatorResult {
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
            val apiResponse = service.getVehicleSearchFor(resourceType, query, page)
            val elements = apiResponse.elements!!
            val endOfPaginationReached = elements.isEmpty() || apiResponse.next == null

            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.vehicleRemoteKeysDao().clearRemoteKeys()
                    database.vehicleDao().clearAll()
                }
                val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = elements.map {
                    VehicleRemoteKeys(id = it.getNumber(), prevKey = prevKey, nextKey = nextKey)
                }
                database.vehicleRemoteKeysDao().insertAll(keys)
                database.vehicleDao().insertAll(elements)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Vehicle>): VehicleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.let { vehicle ->
                database.vehicleRemoteKeysDao().remoteKeysForPerson(vehicle.getNumber())
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Vehicle>): VehicleRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { vehicle ->
                database.vehicleRemoteKeysDao().remoteKeysForPerson(vehicle.getNumber())
            }
    }

    private suspend fun getRemoteKeyForLastTime(state: PagingState<Int, Vehicle>): VehicleRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { vehicle ->
                database.vehicleRemoteKeysDao().remoteKeysForPerson(vehicle.getNumber())
            }
    }

}

fun Vehicle.getNumber(): Long {
    return this.url.split("/")[5].toLong()
}