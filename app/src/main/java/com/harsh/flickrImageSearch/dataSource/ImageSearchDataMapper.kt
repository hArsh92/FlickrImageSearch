package com.harsh.flickrImageSearch.dataSource

import android.net.Uri
import com.harsh.flickrImageSearch.view.adapter.Photo

class ImageSearchDataMapper {

    fun map(data: SearchPhotoDTO): Photo {
        val imageUrl = if (data.canCreateImageUrl()) {
            Uri.Builder()
                .scheme("http")
                .authority("farm${data.farmId}.staticflickr.com")
                .appendPath(data.serverId)
                .appendPath("${data.id}_${data.secret}_m.jpg")
                .build()
                .toString()
        } else {
            ""
        }
        return Photo(
            id = data.id,
            title = data.title.orEmpty(),
            imageUrl = imageUrl
        )
    }
}
