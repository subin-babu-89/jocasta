package com.example.jocasta.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.FragmentFilmDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Film
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.*

class FilmDetailFragment : Fragment() {

    private val viewModel: FilmDetailViewModel by lazy {
        ViewModelProvider(
            this,
            FilmDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(FilmDetailViewModel::class.java)
    }

    private lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        film = FilmDetailFragmentArgs.fromBundle(requireArguments()).film
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmDetailBinding.inflate(inflater)
        binding.film = film
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.peopleList.adapter =
            DetailsPeopleAdapter(DetailsPeopleAdapter.ResourceClickListener {
                val action = FilmDetailFragmentDirections.filmDetailToPeopleDetail(it)
                findNavController().navigate(action)
            })

        viewModel.people.observe(viewLifecycleOwner, {
            val adapter = binding.peopleList.adapter as DetailsPeopleAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.planetList.adapter =
            DetailsPlanetAdapter(DetailsPlanetAdapter.ResourceClickListener {
                val action = FilmDetailFragmentDirections.filmDetailToPlanetDetail(it)
                findNavController().navigate(action)
            })

        viewModel.planets.observe(viewLifecycleOwner, {
            val adapter = binding.planetList.adapter as DetailsPlanetAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.starshipList.adapter =
            DetailsStarshipAdapter(DetailsStarshipAdapter.ResourceClickListener {
                val action = FilmDetailFragmentDirections.filmDetailToStarshipDetail(it)
                findNavController().navigate(action)
            })

        viewModel.starships.observe(viewLifecycleOwner, {
            val adapter = binding.starshipList.adapter as DetailsStarshipAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.vehiclesList.adapter =
            DetailsVehiclesAdapter(DetailsVehiclesAdapter.ResourceClickListener {
                val action = FilmDetailFragmentDirections.filmDetailToVehicleDetail(it)
                findNavController().navigate(action)
            })

        viewModel.vehicles.observe(viewLifecycleOwner, {
            val adapter = binding.vehiclesList.adapter as DetailsVehiclesAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.speciesList.adapter =
            DetailsSpeciesAdapter(DetailsSpeciesAdapter.ResourceClickListener {
                val action = FilmDetailFragmentDirections.filmDetailToSpeciesDetail(it)
                findNavController().navigate(action)
            })

        viewModel.species.observe(viewLifecycleOwner, {
            val adapter = binding.speciesList.adapter as DetailsSpeciesAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })
        return binding.root
    }

}