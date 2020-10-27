package com.harsh.kotlinplayground.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harsh.kotlinplayground.dataSource.ImagePageSourceProvider

class ViewModelFactory(
    private val pageSourceProvider: ImagePageSourceProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageSearchViewModel(pageSourceProvider) as T
    }
}
