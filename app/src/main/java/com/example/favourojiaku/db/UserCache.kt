package com.example.favourojiaku.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.favourojiaku.models.Users
import com.example.favourojiaku.util.AppExecutors

class UserCache(app:Application) {

    private val userDao = AppDb.getDb(app).userDao()
    private val appExecutors = AppExecutors()

     fun insert(users: List<Users>, done: () -> Unit){
        appExecutors.diskIO().execute{
            Log.d("UserCache", "Inserting ${users.size} users")
            userDao.save(users)
            done()
        }
    }

    fun fetchUser(): LiveData<List<Users>>{
        return userDao.fetchUser()
    }
}