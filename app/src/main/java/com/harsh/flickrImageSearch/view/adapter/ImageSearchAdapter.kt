package com.harsh.flickrImageSearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.harsh.flickrImageSearch.R

class ImageSearchAdapter : PagingDataAdapter<Photo, ItemImageResult>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageResult {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_search_result, parent, false)
        return ItemImageResult(itemView)
    }

    override fun onBindViewHolder(holder: ItemImageResult, position: Int) {
        getItem(position)?.let { photo ->
            holder.bindView(photo)
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
