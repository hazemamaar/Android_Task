package com.example.androidtask.domain.usecase

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.repository.UserRepo
import com.example.core.base.usecase.LocalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepo: UserRepo) :LocalUseCase<Long,RegisterModel>(){

    override fun executeLocalDS(body: RegisterModel?): Flow<Long> = flow {
        emit (userRepo.insertUser(body!!))
    }




}