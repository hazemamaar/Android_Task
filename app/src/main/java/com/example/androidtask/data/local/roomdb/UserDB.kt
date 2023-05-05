package com.example.androidtask.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtask.data.local.models.RegisterModel

@Database(entities = [RegisterModel::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract val userDao : UserDao
}