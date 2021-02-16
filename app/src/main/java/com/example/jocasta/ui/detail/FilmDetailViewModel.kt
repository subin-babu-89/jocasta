package com.example.jocasta.ui.detail

import androidx.lifecycle.*
import com.example.jocasta.network.model.*
import com.example.jocasta.repository.ResourceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmDetailViewModel(private val repository: ResourceRepository) : ViewModel() {
    companion object {
        class ViewModelFactory(private val repository: ResourceRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(FilmDetailViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return FilmDetailViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private val _people = MutableLiveData<List<People>>()
    val people: LiveData<List<People>>
        get() = _people

    private val _planets = MutableLiveData<List<Planet>>()
    val planets: LiveData<List<Planet>>
        get() = _planets

    private val _starships = MutableLiveData<List<Starship>>()
    val starships: LiveData<List<Starship>>
        get() = _starships

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>>
        get() = _vehicles

    private val _species = MutableLiveData<List<Species>>()
    val species: LiveData<List<Species>>
        get() = _species

    fun getPeopleDetails(peopleUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<People>()
                peopleUrls.forEach { filmUrl ->
                    val people = repository.getPeopleFor(filmUrl)
                    retrievedLists.add(people)
                }
                _people.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getPlanetDetails(planetUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Planet>()
                planetUrls.forEach { planetUrl ->
                    val planet = repository.getPlanetFor(planetUrl)
                    retrievedLists.add(planet)
                }
                _planets.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getStarshipDetails(starshipUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Starship>()
                starshipUrls.forEach { starshipUrl ->
                    val starship = repository.getStarshipFor(starshipUrl)
                    retrievedLists.add(starship)
                }
                _starships.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }

    fun getVehicleDetails(vehicleUrls: List<String>) {
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

    fun getSpeciesDetails(speciesUrls: List<String>) {
        viewModelScope.launch {
            try {
                val retrievedLists = mutableListOf<Species>()
                speciesUrls.forEach { speciesUrl ->
                    val species = repository.getSpeciesFor(speciesUrl)
                    retrievedLists.add(species)
                }
                _species.value = retrievedLists
            } catch (exception: Exception) {
                Timber.d("some error occured : $exception")
            }
        }
    }
}