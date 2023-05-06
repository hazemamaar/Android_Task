package com.example.androidtask.data.repository

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.data.local.roomdb.UserDao
import com.example.androidtask.data.remote.ApiService
import com.example.androidtask.data.remote.models.ResponseObject
import com.example.androidtask.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao:UserDao,private val apiService: ApiService) : UserRepo{
    override fun getUser(email: String): Flow<RegisterModel> {
        return  userDao.getUser(email)
    }

    override suspend fun insertUser(user: RegisterModel): Long {
        return userDao.insertUser(user)
    }

    override suspend fun getProblems(): ResponseObject {
        return apiService.getProblems()
    }
}