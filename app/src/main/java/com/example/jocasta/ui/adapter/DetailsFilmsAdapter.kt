package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.network.model.Film

class DetailsFilmsAdapter(private val onClickListener: ResourceClickListener) :
    ListAdapter<Film, DetailsFilmsAdapter.SimpleClickableTextViewHolder>(DETAILS_FILMS_COMPARATOR) {
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    class ResourceClickListener(val clickListener: (film: Film) -> Unit) {
        fun onClick(film: Film) {
            return when {
                film.url.isEmpty() -> {
                }
                else -> {
                    clickListener(film)
                }
            }
        }
    }

    class SimpleClickableTextViewHolder(private var binding: SimpleClickableTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.textString = film.title
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
        val item: Film = getItem(position)
        item.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}