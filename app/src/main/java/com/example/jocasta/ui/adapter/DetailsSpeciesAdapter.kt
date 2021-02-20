package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.Species

/**
 * Adapter for the species list that appears on the details page for different resource types
 */
class DetailsSpeciesAdapter(private val onClickListener: ResourceClickListener) :
    ListAdapter<Species, DetailsSpeciesAdapter.SimpleClickableTextViewHolder>(
        DETAILS_FILMS_COMPARATOR
    ) {
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<Species>() {
            override fun areItemsTheSame(oldItem: Species, newItem: Species): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Species, newItem: Species): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ResourceClickListener(val clickListener: (species: Species) -> Unit) {
        fun onClick(species: Species) {
            return when {
                species.url.isEmpty() -> {
                }
                else -> {
                    clickListener(species)
                }
            }
        }
    }

    class SimpleClickableTextViewHolder(private var binding: SimpleClickableTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(species: Species) {
            binding.textString = species.name
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleClickableTextViewHolder {
        return SimpleClickableTextViewHolder(
            SimpleClickableTextViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: SimpleClickableTextViewHolder, position: Int) {
        val item: Species = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}