package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.Planet

class DetailsPlanetAdapter(private val onClickListener : ResourceClickListener) : ListAdapter<Planet, DetailsPlanetAdapter.SimpleClickableTextViewHolder>(DETAILS_FILMS_COMPARATOR){
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<Planet>(){
            override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ResourceClickListener(val clickListener: (planet : Planet) -> Unit){
        fun onClick(planet: Planet) = clickListener(planet)
    }

    class SimpleClickableTextViewHolder(private var binding : SimpleClickableTextViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (planet: Planet){
            binding.textString = planet.name
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
        val item: Planet = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}