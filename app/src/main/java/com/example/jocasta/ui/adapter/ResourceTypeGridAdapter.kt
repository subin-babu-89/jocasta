package com.example.jocasta.ui.adapter

import android.content.res.Resources
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.jocasta.R
import com.example.jocasta.databinding.ResourcesGridItemBinding
import com.example.jocasta.db.entity.ResourceType
import timber.log.Timber

/**
 * Adapter for the list of resources to be displayed on the initial launch screen of the app
 */
class ResourceTypeGridAdapter(private val onClickListener: ResourceClickListener) :
    ListAdapter<ResourceType, ResourceTypeGridAdapter.ResourceViewHolder>(
        RESOURCE_TYPE_DIFF_COMPARATOR
    ) {

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

    class ResourceViewHolder(private var binding: ResourcesGridItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(resource: ResourceType) {
            val imageUrl = getImageUrlFromArray(resource, itemView.resources)
            binding.resource = resource
            binding.resourceImage.let {
                val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(imgUri)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            if (resource != null) {
                                val p = Palette.from(resource).generate()
                                val vibrant = p.mutedSwatch
                                val titleColor = vibrant?.titleTextColor
                                if (titleColor != null) {
                                    binding.resourceType.setTextColor(titleColor)
                                }
                            }
                            return false
                        }
                    })
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.loading_img)
                    )
                    .into(it)
            }
            binding.executePendingBindings()
        }


        private fun getImageUrlFromArray(resourceType: ResourceType, resources: Resources): String {
            val urlArray: Array<String> = resources.getStringArray(R.array.resource_urls)
            return when (resourceType.resourceName) {
                "people" -> urlArray[0]
                "planets" -> urlArray[1]
                "films" -> urlArray[2]
                "species" -> urlArray[3]
                "vehicles" -> urlArray[4]
                "starships" -> urlArray[5]
                else -> urlArray[0]
            }
        }
    }

    companion object {
        private val RESOURCE_TYPE_DIFF_COMPARATOR = object : DiffUtil.ItemCallback<ResourceType>() {
            override fun areItemsTheSame(oldItem: ResourceType, newItem: ResourceType): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ResourceType, newItem: ResourceType): Boolean {
                return oldItem.resourceID == newItem.resourceID
            }

        }
    }

    class ResourceClickListener(val clickListener: (resource: ResourceType) -> Unit) {
        fun onClick(resource: ResourceType) = clickListener(resource)
    }
}