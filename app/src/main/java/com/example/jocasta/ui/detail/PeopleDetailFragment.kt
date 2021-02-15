package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentPeopleDetailBinding
import com.example.jocasta.db.JocastaDatabase
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.SWApiService
import com.example.jocasta.network.model.People
import com.example.jocasta.repository.ResourceListRepository
import com.example.jocasta.repository.ResourceRepository
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.resources.ResourcesViewModel
import timber.log.Timber
import kotlin.time.TimedValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PeopleDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PeopleDetailFragment : Fragment() {

    companion object {

    }

    private val viewModel: PeopleDetailViewModel by lazy {
        ViewModelProvider(this,
            PeopleDetailViewModel.Companion.ViewModelFactory(
                ResourceRepository(SWApiService.create(), JocastaDatabase.getInstance(requireContext()))
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
    ): View? {
        val binding = FragmentPeopleDetailBinding.inflate(inflater)
        binding.person = person
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerView.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
                for (film in viewModel.films.value!!){
                    if (film.title == it){
                        val action = PeopleDetailFragmentDirections.peopleDetailToFilmDetail(film)
                        findNavController().navigate(action)
                    }
                }
        })

        binding.speciesList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            Timber.d("testing for $it")
        })

        binding.vehiclesList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            Timber.d("testing for $it")
        })

        binding.starshipList.adapter = DetailsFilmsAdapter(DetailsFilmsAdapter.ResourceClickListener {
            Timber.d("testing for $it")
        })

        viewModel.films.observe(viewLifecycleOwner, Observer {
            Timber.d("here films ${it.size}")
            val adapter = binding.recyclerView.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            val map: List<String?> = it.map { it -> it.title }
            adapter.submitList(map)
        })

        viewModel.species.observe(viewLifecycleOwner, Observer {
            Timber.d("here species ${it.size}")
            val adapter = binding.speciesList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            val map: List<String?> = it.map { it -> it.name }
            adapter.submitList(map)
        })

        viewModel.vehicles.observe(viewLifecycleOwner, Observer {
            Timber.d("here vehicles ${it.size}")
            val adapter = binding.vehiclesList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            val map: List<String?> = it.map { it -> it.name }
            adapter.submitList(map)
        })

        viewModel.starships.observe(viewLifecycleOwner, Observer {
            Timber.d("here starships ${it.size}")
            val adapter = binding.starshipList.adapter as DetailsFilmsAdapter
            adapter.submitList(null)
            val map: List<String?> = it.map { it -> it.name }
            adapter.submitList(map)
        })
        return binding.root
    }

}
