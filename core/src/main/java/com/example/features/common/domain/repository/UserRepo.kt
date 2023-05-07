package com.example.features.common.domain.repository

import com.example.data.local.models.RegisterModel
import com.example.data.network.models.Medical
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getUser(email:String) : Flow<RegisterModel>
   suspend fun insertUser(user: RegisterModel) :Long

   suspend fun getProblems(): Medical
}