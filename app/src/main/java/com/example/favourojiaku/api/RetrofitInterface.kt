package com.example.favourojiaku.api

import androidx.lifecycle.LiveData
import com.example.favourojiaku.models.Users
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/accounts")
    fun fetchAccounts(): LiveData<Resource<List<Users>>>
}