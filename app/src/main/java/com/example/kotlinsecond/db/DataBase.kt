package com.example.kotlinsecond.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [User::class], version = 1)
abstract class MyDB : RoomDatabase() {
    abstract fun getDao(): Dao

}