package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentFilmDetailBinding
import com.example.jocasta.network.model.Film

/**
 * A simple [Fragment] subclass.
 * Use the [FilmDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilmDetailFragment : Fragment() {

    private lateinit var film : Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        film = FilmDetailFragmentArgs.fromBundle(requireArguments()).film
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilmDetailBinding.inflate(inflater)
        binding.data.text = film.toString()
        return binding.root
    }

}