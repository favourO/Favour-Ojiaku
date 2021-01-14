package com.example.favourojiaku.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.favourojiaku.api.DataBroker
import com.example.favourojiaku.api.Resource
import com.example.favourojiaku.api.RetrofitClient
import com.example.favourojiaku.db.AppDb
import com.example.favourojiaku.db.UserCache
import com.example.favourojiaku.models.Users
import com.example.favourojiaku.util.AppExecutors

class UsersRepository(app: Application) {

    private val cache = UserCache(app)
    private val appExecutors = AppExecutors()
    private val webService = RetrofitClient.getClient(app.applicationContext)
    private val dao = AppDb.getDb(app).userDao()


    fun loadAttendance(): LiveData<Resource<List<Users>>>{
        return object : DataBroker<Users>(appExecutors){
            override fun saveData(items: List<Users>) {
                Log.d("userRepo ", "saveData() -> items size ${items.size}")
                dao.deleteAll()
                dao.save(items)
            }

            override fun shouldFetch(data: List<Users>?): Boolean {
                Log.d("UsersRepo ", "shouldFetch() -> items size ${data?.size}")
                return true
            }

            override fun loadFromDb() = dao.fetchUser()

            override fun createRequest(): LiveData<Resource<List<Users>>>  = webService.fetchAccounts()

            override fun onRequestFailed() {
                super.onRequestFailed()
            }

        }.asLiveData()
    }
}