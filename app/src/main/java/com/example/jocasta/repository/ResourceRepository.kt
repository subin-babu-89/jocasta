package com.example.jocasta.repository

import androidx.paging.*
import com.example.jocasta.db.*
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.*
import com.example.jocasta.repository.mediator.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the resource fetched from SWapi
 */
@Suppress("UNCHECKED_CAST")
class ResourceRepository(private val service: SWApiService, private val database: JocastaDatabase) {

    companion object {
        const val NETWORK_PAGE_SIZE = 5
    }

    /**
     * Request paging data from DB or mediate from an online resource.
     */
    @OptIn(ExperimentalPagingApi::class)
    fun <T : AbstractResource> getSearchResults(
        resourceType: String,
        query: String
    ): Flow<PagingData<T>> {
        val dbQuery = "%${query.replace(' ', '%')}%"

        val pagingSourceFactory =
            getPagingSourceFactory(resourceType, dbQuery) as () -> PagingSource<Int, T>

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = getRemoteMediator(
                resourceType,
                query,
                service,
                database
            ) as RemoteMediator<Int, T>,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


    private fun getPagingSourceFactory(
        resourceType: String,
        dbQuery: String
    ): () -> PagingSource<Int, AbstractResource> {
        return when (resourceType) {
            PEOPLE -> {
                {
                    database.peopleDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            PLANETS -> {
                {
                    database.planetDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            FILMS -> {
                {
                    database.filmDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            SPECIES -> {
                {
                    database.speciesDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            VEHICLES -> {
                {
                    database.vehicleDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            STARSHIPS -> {
                {
                    database.starshipDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
            else -> {
                {
                    database.peopleDao().elementsByName(dbQuery)
                } as () -> PagingSource<Int, AbstractResource>
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getRemoteMediator(
        resourceType: String,
        query: String,
        service: SWApiService,
        database: JocastaDatabase
    ): RemoteMediator<Int, AbstractResource> {
        return when (resourceType) {
            PEOPLE -> {
                PeopleRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            PLANETS -> {
                PlanetRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            FILMS -> {
                FilmRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            SPECIES -> {
                SpeciesRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            VEHICLES -> {
                VehicleRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            STARSHIPS -> {
                StarshipRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
            else -> {
                PeopleRemoteMediator(
                    resourceType,
                    query,
                    service,
                    database
                ) as RemoteMediator<Int, AbstractResource>
            }
        }
    }

    /**
     * get film detail for film with filmUrl
     *
     * @param filmUrl url for the film
     */
    suspend fun getFilmFor(filmUrl: String): Film {
        if (database.filmDao().filmByURL(filmUrl).isNullOrEmpty()) {
            val id = filmUrl.split("/")[5]
            val film = service.getFilmForId(id)
            database.filmDao().insert(film)
        }
        return database.filmDao().filmByURL(filmUrl)[0]
    }

    /**
     * get planet detail for planet with planetUrl
     *
     * @param planetUrl url for the planet
     */
    suspend fun getPlanetFor(planetUrl: String): Planet {
        if (database.filmDao().filmByURL(planetUrl).isNullOrEmpty()) {
            val id = planetUrl.split("/")[5]
            val planet = service.getPlanetForId(id)
            database.planetDao().insert(planet)
        }
        return database.planetDao().elementByUrl(planetUrl)[0]
    }

    /**
     * get species detail for species with speciesUrl
     *
     * @param speciesUrl url for the species
     */
    suspend fun getSpeciesFor(speciesUrl: String): Species {
        if (database.speciesDao().elementByURl(speciesUrl).isNullOrEmpty()) {
            val id = speciesUrl.split("/")[5]
            val species = service.getSpeciesForId(id)
            database.speciesDao().insert(species)
        }
        return database.speciesDao().elementByURl(speciesUrl)[0]
    }

    /**
     * get vehicle detail for vehicle with vehicleUrl
     *
     * @param vehicleUrl url for the vehicle
     */
    suspend fun getVehicleFor(vehicleUrl: String): Vehicle {
        if (database.vehicleDao().elementByURl(vehicleUrl).isNullOrEmpty()) {
            val id = vehicleUrl.split("/")[5]
            val vehicle = service.getVehiclesForId(id)
            database.vehicleDao().insert(vehicle)
        }
        return database.vehicleDao().elementByURl(vehicleUrl)[0]
    }

    /**
     * get starship detail for starship with starshipUrl
     *
     * @param starshipUrl url for the starship
     */
    suspend fun getStarshipFor(starshipUrl: String): Starship {
        if (database.starshipDao().elementByURl(starshipUrl).isNullOrEmpty()) {
            val id = starshipUrl.split("/")[5]
            val starship = service.getStarshipForId(id)
            database.starshipDao().insert(starship)
        }
        return database.starshipDao().elementByURl(starshipUrl)[0]
    }

    /**
     * get people detail for people(person) with people(person)Url
     *
     * @param peopleUrl url for the person(people)
     */
    suspend fun getPeopleFor(peopleUrl: String): People {
        if (database.peopleDao().elementByURl(peopleUrl).isNullOrEmpty()) {
            val id = peopleUrl.split("/")[5]
            val people = service.getPeopleForId(id)
            database.peopleDao().insert(people)
        }
        return database.peopleDao().elementByURl(peopleUrl)[0]
    }
}