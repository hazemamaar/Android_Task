package com.example.androidtask.domain.repository

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.data.remote.models.ResponseObject
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getUser(email:String) : Flow<RegisterModel>
   suspend fun insertUser(user:RegisterModel) :Long

   suspend fun getProblems():ResponseObject
}