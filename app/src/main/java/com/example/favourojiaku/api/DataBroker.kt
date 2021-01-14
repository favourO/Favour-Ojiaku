package com.example.favourojiaku.api

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.favourojiaku.util.AppExecutors

abstract class DataBroker<T> @MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<List<T>>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource){data->
            result.removeSource(dbSource)
            if(shouldFetch(data)){
                fetchFromNetwork(dbSource)
            }else{
                result.addSource(dbSource){newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }


    @MainThread
    private fun setValue(newValue: Resource<List<T>>){
        if(result.value != newValue){
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<List<T>>){
        val apiResponse = createRequest()
        result.addSource(dbSource){
            setValue(Resource.loading(it))
        }
        result.addSource(apiResponse){response  ->
            result.removeSource(dbSource)
            Log.d("Databroker", "RESPONSE == $response")

            when(response.status){
                Status.SUCCESS ->{
                    appExecutors.diskIO().execute {
                        saveData(response.data ?: emptyList())
                        appExecutors.mainThread().execute{
                            result.addSource(loadFromDb()){newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                Status.ERROR ->{
                    onRequestFailed()
                    result.addSource(dbSource){newData ->
                        setValue(Resource.error(response.message ?: "Unknown error", newData))
                    }
                }
            }
        }
    }
    //@WorkerThread
    //protected open fun processResponse(response: ApiSuccessResponse<T>) = response.body
    @WorkerThread
    protected  abstract fun saveData(items: List<T>)

    //Decide whether or not a network request should be made
    @MainThread
    protected abstract fun shouldFetch(data: List<T>?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<List<T>>

    //Make a network request
    @MainThread
    protected abstract fun createRequest(): LiveData<Resource<List<T>>>

    //Called if network request fails.
    protected open fun onRequestFailed(){}

    fun asLiveData(): LiveData<Resource<List<T>>> = result

}