package com.harsh.kotlinplayground

import android.app.Application
import com.harsh.kotlinplayground.dataSource.HikeImageService
import com.harsh.kotlinplayground.dataSource.ImagePageSourceProvider

class HikeApplication : Application() {

    private lateinit var pageSourceProvider: ImagePageSourceProvider

    override fun onCreate() {
        super.onCreate()

        val imageService = HikeImageService.create()
        pageSourceProvider = ImagePageSourceProvider(imageService)
    }

    fun getImagePageSourceProvider() = pageSourceProvider
}
