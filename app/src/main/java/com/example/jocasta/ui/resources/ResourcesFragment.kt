package com.example.jocasta.ui.resources

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.ResourcesFragmentBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.repository.ResourceListRepository
import com.example.jocasta.ui.adapter.ResourceTypeGridAdapter

class ResourcesFragment : Fragment() {

    companion object {
        fun newInstance() = ResourcesFragment()
    }

    private val viewModel: ResourcesViewModel by lazy {
        ViewModelProvider(this,
            ResourcesViewModel.Companion.ViewModelFactory(
                ResourceListRepository(SWApiService.create(), JocastaDatabase.getInstance(requireContext()))
            )
        ).get(ResourcesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ResourcesFragmentBinding.inflate(inflater)
        //val binding = GridViewItemBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.resourceGrid.adapter = ResourceTypeGridAdapter(ResourceTypeGridAdapter.ResourceClickListener {
            viewModel.displayResourceType(it)
        })

        viewModel.navigateToSelectedResourceType.observe(viewLifecycleOwner, Observer {
                if (null !=it){
                    this.findNavController().navigate(ResourcesFragmentDirections.navigateToSearch(it))
                    viewModel.displayResourceTypeComplete()
                }
        })
        return binding.root
    }

}