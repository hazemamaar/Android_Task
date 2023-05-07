package com.example.features.getMedicals.domain.interactors

import android.util.Log
import com.example.core.usecase.RemoteUseCase
import com.example.data.network.models.Medical
import com.example.features.common.domain.repository.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProblemsUseCase @Inject constructor(private val userRepo: UserRepo) : RemoteUseCase<Medical, Any>()  {
    override fun executeRemoteDS(body: Any?): Flow<Medical>  =flow {
        Log.e("mma", "executeRemoteDS: "+userRepo.getProblems() )
        emit(userRepo.getProblems())
    }
}