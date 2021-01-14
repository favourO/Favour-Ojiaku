package com.example.favourojiaku.db

import android.app.Application
import androidx.room.*
import com.example.favourojiaku.models.Users
import com.example.favourojiaku.util.Converter


@Database(entities = [
    Users::class
], version = 5, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun userDao(): UserDao
    companion object {
        fun getDb(app: Application): AppDb {
            return Room.databaseBuilder(app, AppDb::class.java, "Favour_user.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}