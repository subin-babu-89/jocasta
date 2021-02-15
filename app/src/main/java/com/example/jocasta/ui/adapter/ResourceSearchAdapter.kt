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
import com.example.jocasta.databinding.PeopleViewItemBinding
import com.example.jocasta.network.model.AbstractResource
import com.example.jocasta.network.model.People
import timber.log.Timber

class ResourceSearchAdapter : PagingDataAdapter<AbstractResource, ResourceSearchAdapter.ResourceSearchViewHolder>(RESOURCE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceSearchViewHolder {
        return ResourceSearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ResourceSearchViewHolder, position: Int) {
        val person = getItem(position)
        holder.itemView.setOnClickListener {

        }
        person?.let {
            holder.bind(it)
        }
    }

    class ResourceSearchViewHolder (view : View) : RecyclerView.ViewHolder(view){
        private val name: TextView = view.findViewById(R.id.name)
        private var resource: AbstractResource? = null

        init {
            view.setOnClickListener {
                Timber.d("Person -> $resource")
            }
        }

        fun bind(resource : AbstractResource){
            if (resource == null){
                val resources = itemView.resources
                name.text = "Loading"
            }else {
                showPersonData(resource)
            }
        }

        private fun showPersonData(resource: AbstractResource) {
            this.resource = resource
            name.text = resource.name
        }

        companion object{
            fun create(parent: ViewGroup) : ResourceSearchViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.people_view_item, parent, false)
                return ResourceSearchViewHolder(view)
            }
        }

    }

    companion object{
        private val RESOURCE_COMPARATOR = object : DiffUtil.ItemCallback<AbstractResource>(){
            override fun areItemsTheSame(oldItem: AbstractResource, newItem: AbstractResource): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AbstractResource, newItem: AbstractResource): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }


}