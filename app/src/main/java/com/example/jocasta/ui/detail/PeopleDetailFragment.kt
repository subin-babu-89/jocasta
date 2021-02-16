package com.example.jocasta.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.FragmentPeopleDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.People
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.DetailsSpeciesAdapter
import com.example.jocasta.ui.adapter.DetailsStarshipAdapter
import com.example.jocasta.ui.adapter.DetailsVehiclesAdapter


class PeopleDetailFragment : Fragment() {

    private val viewModel: PeopleDetailViewModel by lazy {
        ViewModelProvider(
            this,
            PeopleDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(PeopleDetailViewModel::class.java)
    }

    private lateinit var person: People

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        person = PeopleDetailFragmentArgs.fromBundle(requireArguments()).people
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPeopleDetailBinding.inflate(inflater)
        binding.person = person
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerView.adapter =
            DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
                val action = PeopleDetailFragmentDirections.peopleDetailToFilmDetail(it)
                findNavController().navigate(action)
            })

        binding.speciesList.adapter =
            DetailsSpeciesAdapter(DetailsSpeciesAdapter.ResourceClickListener {
                val action = PeopleDetailFragmentDirections.peopleDetailToSpeciesDetail(it)
                findNavController().navigate(action)
            })

        binding.vehiclesList.adapter =
            DetailsVehiclesAdapter(DetailsVehiclesAdapter.ResourceClickListener {
                val action = PeopleDetailFragmentDirections.peopleDetailToVehicleDetail(it)
                findNavController().navigate(action)
            })

        binding.starshipList.adapter =
            DetailsStarshipAdapter(DetailsStarshipAdapter.ResourceClickListener {
                val action = PeopleDetailFragmentDirections.peopleDetailToStarShip(it)
                findNavController().navigate(action)
            })

        viewModel.films.observe(viewLifecycleOwner, {
            val adapter = binding.recyclerView.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        viewModel.species.observe(viewLifecycleOwner, {
            val adapter = binding.speciesList.adapter as DetailsSpeciesAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        viewModel.vehicles.observe(viewLifecycleOwner, {
            val adapter = binding.vehiclesList.adapter as DetailsVehiclesAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        viewModel.starships.observe(viewLifecycleOwner, {
            val adapter = binding.starshipList.adapter as DetailsStarshipAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })
        return binding.root
    }

}
