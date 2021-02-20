package com.example.jocasta.ui.detail

import androidx.lifecycle.*
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.People
import com.example.jocasta.network.model.Planet
import com.example.jocasta.repository.ResourceRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class VehicleDetailViewModel(private val repository: ResourceRepository) : ViewModel() {
    companion object {
        class ViewModelFactory(private val repository: ResourceRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(VehicleDetailViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return VehicleDetailViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private val _people = MutableLiveData<List<People>>()
    val people: LiveData<List<People>>
        get() = _people

    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = _films

    /**
     * Get People Details from Repo
     *
     * @param peopleUrls List of string urls to fetch the information for
     */
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
                Timber.d("Exception occured : $exception")
            }
        }
    }

    /**
     * Get Film Details from Repo
     *
     * @param filmURLs List of string urls to fetch the information for
     */
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
                Timber.d("Exception occured : $exception")
            }
        }
    }
}