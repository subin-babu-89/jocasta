package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.R
import com.example.jocasta.network.model.*
import com.example.jocasta.ui.search.ResourceSearchFragmentDirections
import timber.log.Timber

/**
 * adapter for the recyclerview used to display list of resources on the search screen
 */
class ResourceSearchAdapter :
    PagingDataAdapter<AbstractResource, ResourceSearchAdapter.ResourceSearchViewHolder>(
        RESOURCE_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceSearchViewHolder {
        return ResourceSearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ResourceSearchViewHolder, position: Int) {
        val resource = getItem(position)
        holder.itemView.setOnClickListener {
            when (resource) {
                is People -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToPeopleDetail(resource))
                }
                is Planet -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToPlanetDetail(resource))
                }
                is Film -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToFilmDetail(resource))
                }
                is Species -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToSpeciesDetail(resource))
                }
                is Vehicle -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToVehicleDetail(resource))
                }
                is Starship -> {
                    it.findNavController()
                        .navigate(ResourceSearchFragmentDirections.navigateToStarshipDetail(resource))
                }
            }
        }
        resource?.let {
            holder.bind(it)
        }
    }

    class ResourceSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private var resource: AbstractResource? = null

        fun bind(resource: AbstractResource) {
            showPersonData(resource)
        }

        private fun showPersonData(resource: AbstractResource) {
            this.resource = resource
            name.text =
                if (resource is Film) resource.title.toString() else resource.name.toString()
        }

        companion object {
            fun create(parent: ViewGroup): ResourceSearchViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.resource_view_item, parent, false)
                return ResourceSearchViewHolder(view)
            }
        }

    }

    companion object {
        private val RESOURCE_COMPARATOR = object : DiffUtil.ItemCallback<AbstractResource>() {
            override fun areItemsTheSame(
                oldItem: AbstractResource,
                newItem: AbstractResource
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AbstractResource,
                newItem: AbstractResource
            ): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}