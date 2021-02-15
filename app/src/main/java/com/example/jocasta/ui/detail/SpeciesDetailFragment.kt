package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentSpeciesDetailBinding
import com.example.jocasta.network.model.Species

/**
 * A simple [Fragment] subclass.
 * Use the [SpeciesDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpeciesDetailFragment : Fragment() {

    private lateinit var species : Species

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        species = SpeciesDetailFragmentArgs.fromBundle(requireArguments()).species
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSpeciesDetailBinding.inflate(inflater)
        binding.data.text = species.toString()
        return binding.root
    }

}