package com.example.androidtask.domain.usecase

import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.repository.UserRepo
import com.example.core.base.usecase.LocalUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepo: UserRepo) :
    LocalUseCase<RegisterModel, String>() {
    override fun executeLocalDS(body: String?): Flow<RegisterModel> = userRepo.getUser(body!!)


}