package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentPeopleDetailBinding
import com.example.jocasta.databinding.FragmentPlanetDetailBinding
import com.example.jocasta.network.model.Planet

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

    private lateinit var planet : Planet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        planet = PlanetDetailFragmentArgs.fromBundle(requireArguments()).planet
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanetDetailBinding.inflate(inflater)
        binding.data.text = planet.toString()
        return binding.root
    }

    companion object {
    }
}