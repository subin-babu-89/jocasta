package com.example.jocasta.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jocasta.databinding.ResourceSearchFragmentBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.SWApiService
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.ResourceLoadStateAdapter
import com.example.jocasta.ui.adapter.ResourceSearchAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import timber.log.Timber

class ResourceSearchFragment : Fragment() {

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
        const val RESOURCE = "resource"
    }

    private var searchJob : Job? = null

    private val viewModel: ResourceSearchViewModel by lazy {
        ViewModelProvider(this,
            ResourceSearchViewModel.Companion.ViewModelFactory(
                ResourceRepository(SWApiService.create(), JocastaDatabase.getInstance(requireContext()))
            )
        ).get(ResourceSearchViewModel::class.java)
    }

    private lateinit var binding : ResourceSearchFragmentBinding
    private val adapter = ResourceSearchAdapter()

    private lateinit var resourceType : ResourceType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        resourceType = ResourceSearchFragmentArgs.fromBundle(arguments!!).resource

        binding = ResourceSearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.resultsList.layoutManager = LinearLayoutManager(requireContext())
        binding.resultsList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        initAdapter()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY)?: DEFAULT_QUERY
        search(query)
        initSearch(query)

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        return binding.root
    }

    private fun initAdapter() {
        binding.resultsList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ResourceLoadStateAdapter{adapter.retry()},
            footer = ResourceLoadStateAdapter{adapter.retry()}
        )

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.resultsList.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    requireContext(),
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun initSearch(str : String) {
        binding.searchSwApi.setText(str)

        binding.searchSwApi.setOnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO){
                updateRepoListFromInput()
                true
            }else{
                false
            }
        }

        binding.searchSwApi.setOnKeyListener { _, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                updateRepoListFromInput()
                true
            }else{
                false
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.distinctUntilChangedBy {
                it.refresh }
                .filter {
                    it.refresh is LoadState.NotLoading }
                .collect {
                binding.resultsList.scrollToPosition(0)
            }
        }
    }

    private fun updateRepoListFromInput() {
        binding.searchSwApi.text.trim().let {
            binding.resultsList.scrollToPosition(0)
            search(it.toString())
        }
    }

    private fun search(query : String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch{
            viewModel.search(resourceType.resourceName, query).collectLatest {
                adapter.submitData(it)
                Timber.d("Current count is ${adapter.itemCount}")
            }
        }
    }
}