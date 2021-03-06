package com.example.jocasta.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.FragmentSpeciesDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Species
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.DetailsPeopleAdapter
import com.example.jocasta.ui.adapter.DetailsPlanetAdapter

/**
 * Fragment class for the film detail view
 */
class SpeciesDetailFragment : Fragment() {

    private val viewModel: SpeciesDetailViewModel by lazy {
        ViewModelProvider(
            this,
            SpeciesDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(SpeciesDetailViewModel::class.java)
    }

    private lateinit var species: Species

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        species = SpeciesDetailFragmentArgs.fromBundle(requireArguments()).species
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSpeciesDetailBinding.inflate(inflater)

        binding.species = species
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        bindAndObserveDetailsLists(binding)
        return binding.root
    }

    private fun bindAndObserveDetailsLists(binding: FragmentSpeciesDetailBinding) {
        // <-----------People Recyclerview on the film details page
        binding.peopleList.adapter =
            DetailsPeopleAdapter(DetailsPeopleAdapter.ResourceClickListener {
                val action = SpeciesDetailFragmentDirections.speciesDetailToPeopleDetail(it)
                findNavController().navigate(action)
            })

        viewModel.people.observe(viewLifecycleOwner, {
            val adapter = binding.peopleList.adapter as DetailsPeopleAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        // <-----------Films Recyclerview on the film details page
        binding.filmsList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            val action = SpeciesDetailFragmentDirections.speciesDetailToFilmDetail(it)
            findNavController().navigate(action)
        })

        viewModel.films.observe(viewLifecycleOwner, {
            val adapter = binding.filmsList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        // <-----------Planet Recyclerview on the film details page
        binding.homeWorld.adapter =
            DetailsPlanetAdapter(DetailsPlanetAdapter.ResourceClickListener {
                val action = SpeciesDetailFragmentDirections.speciesDetailToPlanetDetail(it)
                findNavController().navigate(action)
            })

        viewModel.planets.observe(viewLifecycleOwner, {
            val adapter = binding.homeWorld.adapter as DetailsPlanetAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })
    }

}