package com.harsh.kotlinplayground.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.harsh.kotlinplayground.dataSource.ImagePageSourceProvider
import com.harsh.kotlinplayground.view.adapter.Photo

class ImageSearchViewModel(
    private val pageSourceProvider: ImagePageSourceProvider
) : ViewModel() {

    fun searchImages(searchText: String): LiveData<PagingData<Photo>> {
        return pageSourceProvider.getPageSource(searchText)
            .flow.cachedIn(viewModelScope).asLiveData()
    }
}
