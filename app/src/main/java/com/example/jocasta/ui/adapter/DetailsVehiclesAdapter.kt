package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.Film
import com.example.jocasta.network.model.Vehicle

class DetailsVehiclesAdapter(private val onClickListener : ResourceClickListener) : ListAdapter<Vehicle, DetailsVehiclesAdapter.SimpleClickableTextViewHolder>(DETAILS_FILMS_COMPARATOR){
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<Vehicle>(){
            override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ResourceClickListener(val clickListener: (vehicle : Vehicle) -> Unit){
        fun onClick(vehicle: Vehicle) {
            return when {
                vehicle.url.isEmpty() -> {
                }
                else -> {
                    clickListener(vehicle)
                }
            }
        }
    }

    class SimpleClickableTextViewHolder(private var binding : SimpleClickableTextViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (vehicle: Vehicle){
            binding.textString = vehicle.name
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleClickableTextViewHolder {
        return SimpleClickableTextViewHolder(SimpleClickableTextViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SimpleClickableTextViewHolder, position: Int) {
        val item: Vehicle = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}