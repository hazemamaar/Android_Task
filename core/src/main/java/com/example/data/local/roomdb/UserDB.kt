package com.example.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.models.RegisterModel

@Database(entities = [RegisterModel::class], version = 2, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract val userDao : UserDao
}