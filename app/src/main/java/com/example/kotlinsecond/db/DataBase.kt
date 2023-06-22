package com.example.kotlinsecond.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = arrayOf(User::class), version = 1)
abstract class MyDB : RoomDatabase() {
    abstract fun getDao(): Dao

}