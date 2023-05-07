package com.example.androidtask.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.data.local.models.RegisterModel
import com.example.features.user.domain.interactors.InsertUserUseCase
import com.example.androidtask.android.base.android.Action
import com.example.androidtask.android.base.android.BaseViewModel
import com.example.core.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class RegisterActions : Action {

    object Loading :RegisterActions()
    data class Success(val inserted:Long):RegisterActions()

    data class Failure(val FailMassage:String):RegisterActions()
}
@HiltViewModel
class RegisterViewModel @Inject constructor(private  val insertUserUseCase: InsertUserUseCase) : BaseViewModel<RegisterActions>() {
    fun insertUser(userModel: RegisterModel){


        insertUserUseCase(scope = viewModelScope,userModel){
            when(it){
                is Resource.Failure -> {
                    produce(RegisterActions.Failure(it.message.toString()))
                }
                is Resource.Progress -> {
                    produce(RegisterActions.Loading)
                }
                is Resource.Success -> {
                    produce(RegisterActions.Success(it.data))
                }
            }
        }

    }

}