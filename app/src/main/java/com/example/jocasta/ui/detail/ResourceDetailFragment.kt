package com.example.jocasta.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jocasta.databinding.ResourceDetailFragmentBinding

class ResourceDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ResourceDetailFragment()
    }

    private lateinit var viewModel: ResourceDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ResourceDetailFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResourceDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}