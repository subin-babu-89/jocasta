package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentPeopleDetailBinding
import com.example.jocasta.databinding.FragmentPlanetDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.Planet
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.DetailsPeopleAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetDetailFragment : Fragment() {

    private val viewModel: PlanetDetailViewModel by lazy {
        ViewModelProvider(this,
            PlanetDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(SWApiService.create(), JocastaDatabase.getInstance(requireContext()))
            )
        ).get(PlanetDetailViewModel::class.java)
    }

    private lateinit var planet : Planet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        planet = PlanetDetailFragmentArgs.fromBundle(requireArguments()).planet
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlanetDetailBinding.inflate(inflater)
        binding.planet = planet
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.peopleList.adapter = DetailsPeopleAdapter(DetailsPeopleAdapter.ResourceClickListener {
            val action = PlanetDetailFragmentDirections.planetDetailToPeopleDetail(it)
            findNavController().navigate(action)
        })

        viewModel.people.observe(viewLifecycleOwner, {
            val adapter = binding.peopleList.adapter as DetailsPeopleAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        binding.filmList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            val action = PeopleDetailFragmentDirections.peopleDetailToFilmDetail(it)
            findNavController().navigate(action)
        })

        viewModel.films.observe(viewLifecycleOwner, {
            val adapter = binding.filmList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            adapter.submitList(it)
        })

        return binding.root
    }

    companion object {
    }
}