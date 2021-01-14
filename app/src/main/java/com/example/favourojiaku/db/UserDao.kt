package com.example.favourojiaku.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.favourojiaku.models.Users

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(users: List<Users>)

    @Query("SELECT * FROM users")
    fun fetchUser(): LiveData<List<Users>>

    @Query("DELETE FROM users")
    fun deleteAll()
}