package com.harsh.flickrImageSearch

import android.app.Application
import com.harsh.flickrImageSearch.dataSource.HikeImageService
import com.harsh.flickrImageSearch.dataSource.ImagePageSourceProvider

class HikeApplication : Application() {

    private lateinit var pageSourceProvider: ImagePageSourceProvider

    override fun onCreate() {
        super.onCreate()

        val imageService = HikeImageService.create()
        pageSourceProvider = ImagePageSourceProvider(imageService)
    }

    fun getImagePageSourceProvider() = pageSourceProvider
}
