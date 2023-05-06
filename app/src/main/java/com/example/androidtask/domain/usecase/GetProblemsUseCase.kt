package com.example.androidtask.domain.usecase

import com.example.androidtask.data.remote.models.ResponseObject
import com.example.androidtask.domain.repository.UserRepo
import com.example.core.base.usecase.RemoteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProblemsUseCase @Inject constructor(private val userRepo: UserRepo) : RemoteUseCase<ResponseObject,Any>()  {
    override fun executeRemoteDS(body: Any?): Flow<ResponseObject> =flow {
        emit(userRepo.getProblems())
    }

}