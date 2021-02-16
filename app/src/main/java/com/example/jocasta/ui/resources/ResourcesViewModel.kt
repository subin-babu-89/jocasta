package com.example.jocasta.ui.resources

import androidx.lifecycle.*
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.repository.ResourceListRepository
import kotlinx.coroutines.launch
import timber.log.Timber

enum class SWAPIStatus { LOADING, ERROR, DONE }

class ResourcesViewModel(private val repository: ResourceListRepository) : ViewModel() {

    companion object {
        class ViewModelFactory(private val repository: ResourceListRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ResourcesViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ResourcesViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private val _status = MutableLiveData<SWAPIStatus>()
    val status: LiveData<SWAPIStatus>
        get() = _status

    private val _resources = MutableLiveData<List<ResourceType>>()
    val resources: LiveData<List<ResourceType>>
        get() = _resources

    private val _navigateToSelectedResourceType = MutableLiveData<ResourceType>()
    val navigateToSelectedResourceType: LiveData<ResourceType>
        get() = _navigateToSelectedResourceType

    init {
        getSWApiResources()
    }

    private fun getSWApiResources() {
        viewModelScope.launch {
            _status.value = SWAPIStatus.LOADING
            try {
                _resources.value = repository.getResourcesList()
                _status.value = SWAPIStatus.DONE
                Timber.d("ResourcesViewModel -> complete ${_resources.value}")
            } catch (ex: Exception) {
                Timber.d("ResourcesViewModel -> error $ex")
                _status.value = SWAPIStatus.ERROR
                _resources.value = arrayListOf()
            }
        }
    }

    fun displayResourceType(resourceType: ResourceType) {
        _navigateToSelectedResourceType.value = resourceType
    }

    fun displayResourceTypeComplete() {
        _navigateToSelectedResourceType.value = null
    }
}