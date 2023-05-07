package com.example.features.getMedicals.domain.interactors

import com.example.core.usecase.RemoteUseCase
import com.example.data.network.models.ResponseObject
import com.example.features.common.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProblemsUseCase @Inject constructor(private val userRepo: UserRepo) : RemoteUseCase<ResponseObject, Any>()  {
    override fun executeRemoteDS(body: Any?): Flow<ResponseObject> =flow {
        emit(userRepo.getProblems())
    }
}