package com.harsh.flickrImageSearch.dataSource

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_METHOD = "flickr.photos.search"
private const val API_SECRET = "3e7cc266ae2b0e0d78e279ce8e361736"
private const val API_FORMAT = "json"
private const val JSON_CALLBACK = 1

interface HikeImageService {

    @GET("services/rest?method=$API_METHOD&api_key=$API_SECRET&format=$API_FORMAT&nojsoncallback=$JSON_CALLBACK")
    suspend fun searchImage(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
        @Query("text") searchText: String
    ): SearchResponse

    companion object {
        private const val BASE_URL = "https://api.flickr.com/"

        fun create(): HikeImageService {
            val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d("OkHttp", message)
                }
            })
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(HikeImageService::class.java)
        }
    }
}