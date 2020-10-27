package com.harsh.flickrImageSearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.harsh.flickrImageSearch.dataSource.ImagePageSourceProvider
import com.harsh.flickrImageSearch.view.ImageSearchViewModel
import com.harsh.flickrImageSearch.view.SearchTextWatcher
import com.harsh.flickrImageSearch.view.ViewModelFactory
import com.harsh.flickrImageSearch.view.adapter.ImageSearchAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val GRID_COLUMN_COUNT = 3

class ImageSearchActivity : AppCompatActivity() {

    private lateinit var imageSearchAdapter: ImageSearchAdapter
    private val pageSourceProvider: ImagePageSourceProvider by lazy {
        (application as HikeApplication).getImagePageSourceProvider()
    }
    private val searchResultViewModel: ImageSearchViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(pageSourceProvider))
            .get(ImageSearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupList()
        addListeners(searchResultViewModel)
    }

    private fun setupList() {
        rv_search_result.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, GRID_COLUMN_COUNT)
        rv_search_result.layoutManager = gridLayoutManager
        imageSearchAdapter = ImageSearchAdapter()
        rv_search_result.adapter = imageSearchAdapter
    }

    private fun addListeners(imageSearchViewModel: ImageSearchViewModel) {
        val textWatcher = SearchTextWatcher { searchText ->
            lifecycleScope.launch(Dispatchers.Main) {
                imageSearchViewModel.searchImages(searchText)
                    .observe(this@ImageSearchActivity) { searchResults ->
                        imageSearchAdapter.submitData(lifecycle, searchResults)
                    }
            }
        }
        et_search_view.addTextChangedListener(textWatcher)
    }
}