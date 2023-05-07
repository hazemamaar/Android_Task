package com.example.features.user.domain.interactors

import com.example.core.usecase.LocalUseCase
import com.example.data.local.models.RegisterModel
import com.example.features.common.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepo: UserRepo) :
    LocalUseCase<Long, RegisterModel>() {
    override fun executeLocalDS(body: RegisterModel?): Flow<Long> = flow {
        emit(userRepo.insertUser(body!!))
    }
}