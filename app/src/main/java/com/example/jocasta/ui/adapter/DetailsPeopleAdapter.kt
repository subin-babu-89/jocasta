package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.People

/**
 * Adapter for the people list that appears on the details page for different resource types
 */
class DetailsPeopleAdapter(private val onClickListener: ResourceClickListener) :
    ListAdapter<People, DetailsPeopleAdapter.SimpleClickableTextViewHolder>(DETAILS_FILMS_COMPARATOR) {
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: People, newItem: People): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class ResourceClickListener(val clickListener: (people: People) -> Unit) {
        fun onClick(people: People) {
            return when {
                people.url.isEmpty() -> {
                }
                else -> {
                    clickListener(people)
                }
            }
        }
    }

    class SimpleClickableTextViewHolder(private var binding: SimpleClickableTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(people: People) {
            binding.textString = people.name
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
        val item: People = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}