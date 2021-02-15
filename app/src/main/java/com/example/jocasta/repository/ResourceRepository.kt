package com.example.jocasta.repository

import androidx.paging.*
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.*
import com.example.jocasta.repository.mediator.*
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
            "species" -> {
                { database.speciesDao().elementsByName(dbQuery)} as () -> PagingSource<Int, AbstractResource>
            }
            "vehicles" -> {
                { database.vehicleDao().elementsByName(dbQuery)} as () -> PagingSource<Int, AbstractResource>
            }
            "starships" -> {
                { database.starshipDao().elementsByName(dbQuery)} as () -> PagingSource<Int, AbstractResource>
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
                FilmRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            "species" -> {
                SpeciesRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, AbstractResource>
             }
            "vehicles" -> {
                VehicleRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            "starships" -> {
                StarshipRemoteMediator(resourceType, query, service, database) as RemoteMediator<Int, AbstractResource>
            }
            else -> {
                PeopleRemoteMediator(query, service, database) as RemoteMediator<Int, AbstractResource>
            }
        }
    }

    suspend fun getFilmFor(filmUrl : String) : Film{
        if(database.filmDao().filmByURL(filmUrl).isNullOrEmpty()){
            val id = filmUrl.split("/")[5]
            val film = service.getFilmForId(id)
            database.filmDao().insert(film)
        }
        return database.filmDao().filmByURL(filmUrl)[0]
    }

    suspend fun getSpeciesFor(speciesUrl : String) : Species{
        if(database.speciesDao().elementByURl(speciesUrl).isNullOrEmpty()){
            val id = speciesUrl.split("/")[5]
            val species = service.getSpeciesForId(id)
            database.speciesDao().insert(species)
        }
        return database.speciesDao().elementByURl(speciesUrl)[0]
    }

    suspend fun getVehicleFor(vehicleUrl : String) : Vehicle{
        if(database.vehicleDao().elementByURl(vehicleUrl).isNullOrEmpty()){
            val id = vehicleUrl.split("/")[5]
            val vehicle = service.getVehiclesForId(id)
            database.vehicleDao().insert(vehicle)
        }
        return database.vehicleDao().elementByURl(vehicleUrl)[0]
    }

    suspend fun getStarshipFor(starshipUrl : String) : Starship{
        if(database.starshipDao().elementByURl(starshipUrl).isNullOrEmpty()){
            val id = starshipUrl.split("/")[5]
            val starship = service.getStarshipForId(id)
            database.starshipDao().insert(starship)
        }
        return database.starshipDao().elementByURl(starshipUrl)[0]
    }

    suspend fun getPeopleFor(peopleUrl : String) : People{
        if(database.peopleDao().elementByURl(peopleUrl).isNullOrEmpty()){
            val id = peopleUrl.split("/")[5]
            val people = service.getPeopleForId(id)
            database.peopleDao().insert(people)
        }
        return database.peopleDao().elementByURl(peopleUrl)[0]
    }
}