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
import com.example.jocasta.network.model.People
import timber.log.Timber

class ResourceSearchAdapter : PagingDataAdapter<People, ResourceSearchAdapter.ResourceSearchViewHolder>(RESOURCE_COMPARATOR) {

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
        private var person: People? = null

        init {
            view.setOnClickListener {
                Timber.d("Person -> $person")
            }
        }

        fun bind(person : People){
            if (person == null){
                val resources = itemView.resources
                name.text = "Loading"
            }else {
                showPersonData(person)
            }
        }

        private fun showPersonData(person: People) {
            this.person = person
            name.text = person.name
        }

        companion object{
            fun create(parent: ViewGroup) : ResourceSearchViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.people_view_item, parent, false)
                return ResourceSearchViewHolder(view)
            }
        }

    }

    companion object{
        private val RESOURCE_COMPARATOR = object : DiffUtil.ItemCallback<People>(){
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.peopleID == newItem.peopleID
            }
        }
    }


}