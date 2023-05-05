package com.example.androidtask.domain.repository

import com.example.androidtask.data.local.models.RegisterModel
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getUser(email:String) : Flow<RegisterModel>
   suspend fun insertUser(user:RegisterModel) :Long
}