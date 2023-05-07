package com.example.androidtask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.data.local.models.RegisterModel
import com.example.features.user.domain.interactors.GetUserUseCase
import com.example.androidtask.android.base.android.Action
import com.example.androidtask.android.base.android.BaseViewModel
import com.example.core.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class LoginActions : Action {
    object Loading : LoginActions()
    data class Success(val userModel: RegisterModel) : LoginActions()

    data class Failure(val FailMassage: String) : LoginActions()
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    BaseViewModel<LoginActions>() {
    fun getUser(email: String) {
        getUserUseCase(viewModelScope, email) {
            when (it) {
                is Resource.Failure -> {
                    produce(LoginActions.Failure(it.message.toString()))
                }

                is Resource.Progress -> {
                    if (!it.loading)
                        produce(LoginActions.Loading)
                }

                is Resource.Success -> {
                    Log.e("success", "handleUiState: " + it.data.password)
                    produce(LoginActions.Success(it.data))
                }

                else -> {}
            }
        }


    }
}