package com.example.kotlinsecond.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity (tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "wins", defaultValue = "0")
    var wins: Int
        )