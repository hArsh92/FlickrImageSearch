package com.harsh.flickrImageSearch.dataSource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.harsh.flickrImageSearch.view.adapter.Photo

class ImagePageSourceProvider(
    private val imageService: HikeImageService
) {

    fun getPageSource(searchText: String): Pager<Int, Photo> {
        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_LOAD_SIZE
        )
        return Pager(
            config = pagingConfig,
            initialKey = FIRST_PAGE_KEY,
            pagingSourceFactory = { ImageSearchDataSource(searchText, imageService) }
        )
    }

    companion object {
        private const val FIRST_PAGE_KEY = 1
        private const val PAGE_SIZE = 30
        private const val INITIAL_LOAD_SIZE = 45
    }
}
