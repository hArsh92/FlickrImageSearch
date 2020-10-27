package com.harsh.kotlinplayground.dataSource

import androidx.paging.PagingSource
import com.harsh.kotlinplayground.view.adapter.Photo

class ImageSearchDataSource(
    private val searchText: String,
    private val imageService: HikeImageService
) : PagingSource<Int, Photo>() {

    private val mapper = ImageSearchDataMapper()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            when (params) {
                is LoadParams.Refresh,
                is LoadParams.Append,
                is LoadParams.Prepend -> {
                    val result = imageService.searchImage(
                        pageSize = params.loadSize,
                        page = params.key ?: 1,
                        searchText = searchText
                    ).photos
                    val photos = result.photo.map { mapper.map(it) }
                    val prevKey = if (result.page > 1) result.page - 1 else null
                    val nextKey = if (result.pages > result.page) result.page + 1 else null
                    LoadResult.Page(
                        data = photos,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
            }
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}
