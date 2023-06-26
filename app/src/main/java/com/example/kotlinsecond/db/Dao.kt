package com.example.kotlinsecond.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert
    fun insert(vararg user: User)
    @Query("SELECT * FROM users ORDER BY wins DESC LIMIT 17" )
    fun getAll(): LiveData<List<User>>
    @Query("SELECT * FROM users WHERE username = :username")
    fun getUser(username: String): User
    @Update
    fun update(user: User)
}