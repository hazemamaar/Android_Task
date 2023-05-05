package com.example.androidtask.domain.usecase

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepo: UserRepo) {
    operator fun invoke(email: String): Flow<RegisterModel> =
        userRepo.getUser(email)

}