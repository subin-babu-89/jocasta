package com.example.jocasta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.R
import com.example.jocasta.databinding.ResourceLoadStateFooterViewItemBinding

class ResourceLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ResourceLoadStateAdapter.ResourceLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ResourceLoadStateViewHolder {
        return ResourceLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: ResourceLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class ResourceLoadStateViewHolder(
        private val binding: ResourceLoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): ResourceLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.resource_load_state_footer_view_item, parent, false)
                val binding = ResourceLoadStateFooterViewItemBinding.bind(view)
                return ResourceLoadStateViewHolder(binding, retry)
            }
        }

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMsg.isVisible = loadState !is LoadState.Loading
        }
    }


}