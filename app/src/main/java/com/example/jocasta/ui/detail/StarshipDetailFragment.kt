package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentStarshipDetailBinding
import com.example.jocasta.network.model.Starship

/**
 * A simple [Fragment] subclass.
 * Use the [StarshipDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StarshipDetailFragment : Fragment() {

    private lateinit var starShip : Starship

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        starShip = StarshipDetailFragmentArgs.fromBundle(requireArguments()).starship
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarshipDetailBinding.inflate(inflater)
        binding.data.text = starShip.toString()
        return binding.root
    }

}