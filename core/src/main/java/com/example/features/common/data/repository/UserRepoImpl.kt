package com.example.features.common.data.repository

import com.example.data.local.models.RegisterModel
import com.example.data.local.roomdb.UserDao
import com.example.data.network.ApiService
import com.example.data.network.models.ResponseObject
import com.example.features.common.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao, private val apiService: ApiService) :
    UserRepo {
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