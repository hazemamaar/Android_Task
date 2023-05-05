package com.example.androidtask.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_table")
data class RegisterModel(
    val name:String,
    val email:String,
    @PrimaryKey
    val mobileNumber: String,
    val password:String
)
