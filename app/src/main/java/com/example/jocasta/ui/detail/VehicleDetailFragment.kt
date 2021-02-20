package com.example.jocasta.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.FragmentVehicleDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Vehicle
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.DetailsPeopleAdapter

/**
 * Fragment class for the vehicle detail view
 */
class VehicleDetailFragment : Fragment() {

    private val viewModel: VehicleDetailViewModel by lazy {
        ViewModelProvider(
            this,
            VehicleDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(VehicleDetailViewModel::class.java)
    }

    private lateinit var vehicle: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vehicle = VehicleDetailFragmentArgs.fromBundle(requireArguments()).vehicle
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVehicleDetailBinding.inflate(inflater)
        binding.vehicle = vehicle
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        bindAndObserveDetailsLists(binding)

        return binding.root
    }

    private fun bindAndObserveDetailsLists(binding: FragmentVehicleDetailBinding) {
        // <-----------People Recyclerview on the film details page
        binding.pilotList.adapter =
            DetailsPeopleAdapter(DetailsPeopleAdapter.ResourceClickListener {
                val action = VehicleDetailFragmentDirections.vehicleDetailToPeopleDetail(it)
                findNavController().navigate(action)
            })

        viewModel.people.observe(viewLifecycleOwner, {
            val adapter = binding.pilotList.adapter as DetailsPeopleAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        // <-----------Films Recyclerview on the film details page
        binding.filmList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            val action = VehicleDetailFragmentDirections.vehicleDetailToFilmDetail(it)
            findNavController().navigate(action)
        })

        viewModel.films.observe(viewLifecycleOwner, {
            val adapter = binding.filmList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })
    }

}