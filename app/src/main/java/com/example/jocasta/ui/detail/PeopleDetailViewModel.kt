package com.example.jocasta.ui.detail

import androidx.lifecycle.*
import com.example.jocasta.network.model.*
import com.example.jocasta.repository.ResourceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class PeopleDetailViewModel(private val repository: ResourceRepository) : ViewModel() {

    companion object {
        class ViewModelFactory(private val repository: ResourceRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PeopleDetailViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return PeopleDetailViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = _films

    private val _species = MutableLiveData<List<Species>>()
    val species: LiveData<List<Species>>
        get() = _species

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>>
        get() = _vehicles

    private val _starships = MutableLiveData<List<Starship>>()
    val starships: LiveData<List<Starship>>
        get() = _starships

    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>>
        get() = _planets

    fun getFilmDetails(filmURLs: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Film>()
                filmURLs.forEach { filmUrl ->
                    val film = repository.getFilmFor(filmUrl)
                    retrievedLists.add(film)
                }
                _films.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getSpeciesDetails(speciesURLs: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Species>()
                speciesURLs.forEach { speciesURL ->
                    val species = repository.getSpeciesFor(speciesURL)
                    retrievedLists.add(species)
                }
                _species.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getVehicles(vehicleUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Vehicle>()
                vehicleUrls.forEach { vehicleUrl ->
                    val vehicle = repository.getVehicleFor(vehicleUrl)
                    retrievedLists.add(vehicle)
                }
                _vehicles.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getStarships(starshipUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Starship>()
                starshipUrls.forEach { starshipUrl ->
                    val starShip = repository.getStarshipFor(starshipUrl)
                    retrievedLists.add(starShip)
                }
                _starships.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getPlanet(planetUrls : List<String>){
        viewModelScope.launch {
            try {
                val retrievedList = mutableListOf<Planet>()
                planetUrls.forEach { planetUrl ->
                    val planet = repository.getPlanetFor(planetUrl)
                    retrievedList.add(planet)
                }
                _planets.value = retrievedList
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }
}