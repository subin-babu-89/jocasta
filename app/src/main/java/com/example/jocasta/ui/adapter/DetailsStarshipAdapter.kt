package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.Starship

class DetailsStarshipAdapter(private val onClickListener: ResourceClickListener) :
    ListAdapter<Starship, DetailsStarshipAdapter.SimpleClickableTextViewHolder>(
        DETAILS_FILMS_COMPARATOR
    ) {
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<Starship>() {
            override fun areItemsTheSame(oldItem: Starship, newItem: Starship): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Starship, newItem: Starship): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ResourceClickListener(val clickListener: (starship: Starship) -> Unit) {
        fun onClick(starship: Starship) {
            return when {
                starship.url.isEmpty() -> {
                }
                else -> {
                    clickListener(starship)
                }
            }
        }
    }

    class SimpleClickableTextViewHolder(private var binding: SimpleClickableTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(starship: Starship) {
            binding.textString = starship.name
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
        val item: Starship = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}