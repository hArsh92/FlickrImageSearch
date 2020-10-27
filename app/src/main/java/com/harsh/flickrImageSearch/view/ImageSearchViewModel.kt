package com.harsh.flickrImageSearch.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harsh.flickrImageSearch.dataSource.ImagePageSourceProvider
import com.harsh.flickrImageSearch.view.adapter.Photo

class ImageSearchViewModel(
    private val pageSourceProvider: ImagePageSourceProvider
) : ViewModel() {

    fun searchImages(searchText: String): LiveData<PagingData<Photo>> {
        return pageSourceProvider.getPageSource(searchText)
            .flow.cachedIn(viewModelScope).asLiveData()
    }
}
