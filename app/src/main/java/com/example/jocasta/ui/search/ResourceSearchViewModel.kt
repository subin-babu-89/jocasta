package com.example.jocasta.ui.search

import android.app.DownloadManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jocasta.network.model.*
import com.example.jocasta.repository.ResourceRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class ResourceSearchViewModel(private val repository: ResourceRepository) : ViewModel() {

    companion object {

        class ViewModelFactory(private val repository: ResourceRepository) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ResourceSearchViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ResourceSearchViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

    private var _currentQueryValue : String? = null
    private var _currentSearchResult : Flow<PagingData<AbstractResource>>? = null

    fun search(resourceType : String , query: String) : Flow<PagingData<AbstractResource>> {
        Timber.d("Resource type -> $resourceType")
        val lastResult = _currentSearchResult

        if (query == _currentQueryValue && lastResult != null)
            return lastResult

        _currentQueryValue = query

        when(resourceType){
            "people" -> {
                val newResult = repository.getSearchResults<People>(resourceType, query).cachedIn(viewModelScope)
                _currentSearchResult = newResult as Flow<PagingData<AbstractResource>>
                return newResult
            }
            "planets" -> {
                val newResult = repository.getSearchResults<Planet>(resourceType, query).cachedIn(viewModelScope)
                _currentSearchResult = newResult as Flow<PagingData<AbstractResource>>
                return newResult
            }
            "films" -> {
                val newResult = repository.getSearchResults<Film>(resourceType, query).cachedIn(viewModelScope)
                _currentSearchResult = newResult as Flow<PagingData<AbstractResource>>
                return newResult
            }
            "species" -> {
                val newResult = repository.getSearchResults<Species>(resourceType, query).cachedIn(viewModelScope)
                _currentSearchResult = newResult as Flow<PagingData<AbstractResource>>
                return newResult
            }
            else -> {
                val newResult = repository.getSearchResults<People>(resourceType, query).cachedIn(viewModelScope)
                _currentSearchResult = newResult as Flow<PagingData<AbstractResource>>
                return newResult
            }
        }
    }
}