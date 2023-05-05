package com.example.androidtask.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.domain.usecase.InsertUserUseCase
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

sealed class RegisterActions :Action{
    data class Success(val inserted:Long):RegisterActions()
}
@HiltViewModel
class RegisterViewModel @Inject constructor(private  val insertUserUseCase: InsertUserUseCase) : BaseViewModel<RegisterActions>() {
    fun insertUser(userModel:RegisterModel){

        insertUserUseCase(userModel).onEach {
            produce( RegisterActions.Success(it))
        }.launchIn(viewModelScope)

    }

}