package com.example.favourojiaku.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.favourojiaku.models.Users

@Database(entities = [
    Users::class
], version = 1, exportSchema = false)
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