package com.example.features.user.domain.interactors

import com.example.core.usecase.LocalUseCase
import com.example.data.local.models.RegisterModel
import com.example.features.common.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepo: UserRepo) :
    LocalUseCase<RegisterModel, String>() {
    override fun executeLocalDS(body: String?): Flow<RegisterModel> = userRepo.getUser(body!!)
}