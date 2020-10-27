package com.harsh.kotlinplayground.dataSource

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("photos") val photos: SearchPhotosResponse,
    @SerializedName("stat") val stat: String
)

data class SearchPhotosResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Long,
    @SerializedName("perpage") val perPage: Int,
    @SerializedName("total") val total: String,
    @SerializedName("photo") val photo: List<SearchPhotoDTO>
)

data class SearchPhotoDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String?,
    @SerializedName("server") val serverId: String?,
    @SerializedName("farm") val farmId: String?,
    @SerializedName("secret") val secret: String?
) {
    fun canCreateImageUrl(): Boolean {
        return id.isNullOrBlank().not() &&
                serverId.isNullOrBlank().not() &&
                farmId.isNullOrBlank().not() &&
                secret.isNullOrBlank().not()
    }
}
