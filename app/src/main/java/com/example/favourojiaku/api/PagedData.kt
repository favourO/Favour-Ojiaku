package com.example.favourojiaku.api

import com.google.gson.annotations.SerializedName

data class PagedData<T> (
    @SerializedName("data") val data: List<T>?,
    @SerializedName("current_page") val page: Int,
    @SerializedName("per_page") val limit: Int,
    @SerializedName("total") val total: Int
)