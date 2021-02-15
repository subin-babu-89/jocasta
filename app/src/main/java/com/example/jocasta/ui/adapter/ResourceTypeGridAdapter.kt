package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.ResourcesGridItemBinding
import com.example.jocasta.db.entity.ResourceType
import timber.log.Timber

class ResourceTypeGridAdapter(private val onClickListener : ResourceClickListener) : ListAdapter<ResourceType, ResourceTypeGridAdapter.ResourceViewHolder>(RESOURCE_TYPE_DIFF_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        return ResourceViewHolder(ResourcesGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val resource = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(resource)
        }
        holder.bind(resource)
    }

    override fun submitList(list: MutableList<ResourceType>?) {
        super.submitList(list)
        Timber.d("received data : $list")
    }

    class ResourceViewHolder(private var binding : ResourcesGridItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(resource : ResourceType){
            binding.resource = resource
            binding.executePendingBindings()
        }
    }

    companion object{
        private val RESOURCE_TYPE_DIFF_COMPARATOR = object : DiffUtil.ItemCallback<ResourceType>(){
            override fun areItemsTheSame(oldItem: ResourceType, newItem: ResourceType): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResourceType, newItem: ResourceType): Boolean {
                return oldItem.resourceID == newItem.resourceID
            }

        }
    }

    class ResourceClickListener(val clickListener: (resource : ResourceType) -> Unit){
        fun onClick(resource : ResourceType) = clickListener(resource)
    }
}