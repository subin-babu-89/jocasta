package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentPeopleDetailBinding
import com.example.jocasta.network.model.People

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
        binding.data.text = person.toString()
        return binding.root
    }


}