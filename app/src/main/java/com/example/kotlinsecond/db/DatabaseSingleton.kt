package com.example.kotlinsecond.db

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    var db: MyDB? = null

    public fun getDB(context: Context):MyDB {
        if (db == null){
            db = Room.databaseBuilder(
                context,
                MyDB::class.java,
                "weather.db").build()
        }

        return db as MyDB
    }
}