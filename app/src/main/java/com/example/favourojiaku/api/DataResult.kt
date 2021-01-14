package com.example.favourojiaku.api

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class DataResult<T> (
    val data: LiveData<PagedList<T>>,
    val networkErrors: LiveData<String>,
    val loadingState: LiveData<Boolean>
)