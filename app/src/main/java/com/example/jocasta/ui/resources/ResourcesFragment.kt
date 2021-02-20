package com.example.jocasta.ui.resources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.ResourcesFragmentBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.repository.ResourceListRepository
import com.example.jocasta.ui.adapter.ResourceTypeGridAdapter

/**
 * Fragment class for the resources list that is visible at the start of the app launch
 */
class ResourcesFragment : Fragment() {

    private val viewModel: ResourcesViewModel by lazy {
        ViewModelProvider(
            this,
            ResourcesViewModel.Companion.ViewModelFactory(
                ResourceListRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(ResourcesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ResourcesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.resourceGrid.adapter =
            ResourceTypeGridAdapter(ResourceTypeGridAdapter.ResourceClickListener {
                this.findNavController().navigate(ResourcesFragmentDirections.navigateToSearch(it))
            })
        return binding.root
    }

}