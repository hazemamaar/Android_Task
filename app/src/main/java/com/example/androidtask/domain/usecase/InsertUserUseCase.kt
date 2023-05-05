package com.example.androidtask.domain.usecase

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.repository.UserRepo
import com.example.core.base.usecase.BaseLocalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(user: RegisterModel): Flow<Long> =flow{
        try {
            val userInserted=userRepo.insertUser(user)
            emit(userInserted)
        }catch(e:Exception){
            emit(0)
        }
    }


}