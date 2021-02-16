package com.example.jocasta.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.databinding.FragmentStarshipDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Starship
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.DetailsPeopleAdapter

class StarshipDetailFragment : Fragment() {

    private val viewModel: StarshipDetailViewModel by lazy {
        ViewModelProvider(
            this,
            StarshipDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(
                    SWApiService.create(),
                    JocastaDatabase.getInstance(requireContext())
                )
            )
        ).get(StarshipDetailViewModel::class.java)
    }

    private lateinit var starShip: Starship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        starShip = StarshipDetailFragmentArgs.fromBundle(requireArguments()).starship
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStarshipDetailBinding.inflate(inflater)
        binding.starship = starShip
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.pilotList.adapter =
            DetailsPeopleAdapter(DetailsPeopleAdapter.ResourceClickListener {
                val action = StarshipDetailFragmentDirections.starshipDetailToPeopleDetail(it)
                findNavController().navigate(action)
            })

        viewModel.people.observe(viewLifecycleOwner, {
            val adapter = binding.pilotList.adapter as DetailsPeopleAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.filmList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            val action = StarshipDetailFragmentDirections.starshipDetailToFilmDetail(it)
            findNavController().navigate(action)
        })

        viewModel.films.observe(viewLifecycleOwner, {
            val adapter = binding.filmList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        return binding.root
    }

}