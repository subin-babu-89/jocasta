package com.example.jocasta.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.R
import com.example.jocasta.databinding.FragmentVehicleDetailBinding
import com.example.jocasta.network.model.Vehicle

/**
 * A simple [Fragment] subclass.
 * Use the [VehicleDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehicleDetailFragment : Fragment() {

    private lateinit var vehicle : Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vehicle = VehicleDetailFragmentArgs.fromBundle(requireArguments()).vehicle
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVehicleDetailBinding.inflate(inflater)
        binding.data.text = vehicle.toString()
        return binding.root
    }

}