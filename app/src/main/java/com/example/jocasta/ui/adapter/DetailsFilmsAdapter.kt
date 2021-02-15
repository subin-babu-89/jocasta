package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.databinding.SimpleClickableTextViewBinding
import com.example.jocasta.db.entity.ResourceType

class DetailsFilmsAdapter(private val onClickListener : DetailsFilmsAdapter.ResourceClickListener) : ListAdapter<String, DetailsFilmsAdapter.SimpleClickableTextViewHolder>(DETAILS_FILMS_COMPARATOR){
    companion object {
        private val DETAILS_FILMS_COMPARATOR = object : DiffUtil.ItemCallback<String>(){
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ResourceClickListener(val clickListener: (string: String) -> Unit){
        fun onClick(string : String) = clickListener(string)
    }

    class SimpleClickableTextViewHolder(private var binding : SimpleClickableTextViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (text : String){
            binding.textString = text
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
        val item = getItem(position)
        item?.let {
            holder.itemView.setOnClickListener {
                onClickListener.onClick(item)
            }
            holder.bind(item)
        }
    }
}