package com.example.favourojiaku.ui.users

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.favourojiaku.api.Resource
import com.example.favourojiaku.models.Users
import com.example.favourojiaku.repository.UsersRepository

class UsersViewModel(private val app: Application) : ViewModel() {

    private val repository = UsersRepository(app)
    private val querryLiveData = MutableLiveData<String>()
    private val result: LiveData<LiveData<Resource<List<Users>>>> = Transformations.map(querryLiveData){
        repository.loadUsers()
    }

    val users: LiveData<Resource<List<Users>>> = Transformations.switchMap(result){it}

    fun fetchData(){
        querryLiveData.postValue("fetch")
    }
}

