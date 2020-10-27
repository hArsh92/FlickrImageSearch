package com.harsh.kotlinplayground.view.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_search_result.view.*

class ItemImageResult(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val ivSearchImage: ImageView = itemView.iv_search_image

    fun bindView(photo: Photo) {
        Picasso.get().load(photo.imageUrl)
            .into(ivSearchImage)
    }
}
