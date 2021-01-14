package com.example.favourojiaku.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppCenter : Application() {

    override fun onCreate() {
        super.onCreate()
        initCalligraphy()
    }

    private fun initCalligraphy() {

    }


    companion object {
        fun getViewModelFactory(app: Application): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return modelClass.getConstructor(Application::class.java)
                            .newInstance(app)
                }
            }
        }
    }
}