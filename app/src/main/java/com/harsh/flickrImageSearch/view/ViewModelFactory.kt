package com.harsh.flickrImageSearch.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harsh.flickrImageSearch.dataSource.ImagePageSourceProvider

class ViewModelFactory(
    private val pageSourceProvider: ImagePageSourceProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageSearchViewModel(pageSourceProvider) as T
    }
}
