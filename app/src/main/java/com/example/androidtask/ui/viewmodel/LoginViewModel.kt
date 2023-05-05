package com.example.androidtask.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.usecase.GetUserUseCase
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


sealed class LoginActions : Action {
    data class Success(val user:RegisterModel):LoginActions()
}
@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :BaseViewModel<LoginActions>(){
    fun getUser(email:String){
        getUserUseCase(email).onEach {
            produce( LoginActions.Success(it))
        }.launchIn(viewModelScope)

    }
}