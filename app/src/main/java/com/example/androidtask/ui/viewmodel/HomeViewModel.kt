package com.example.androidtask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.data.remote.models.ResponseObject
import com.example.androidtask.domain.usecase.GetProblemsUseCase
import com.example.core.base.android.Action
import com.example.core.base.android.BaseViewModel
import com.example.core.response.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class HomeActions() : Action {
    object Loading : HomeActions()
    data class Success(val responseObject: ResponseObject) : HomeActions()

    data class Failure(val FailMassage: String) : HomeActions()
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val getProblemsUseCase: GetProblemsUseCase) :
    BaseViewModel<HomeActions>() {
    fun getProblems() {
        getProblemsUseCase(viewModelScope) {
            when (it) {
                is Resource.Failure -> {
                    Log.e("failure", "handleUiState: " + it.message)

                }

                is Resource.Progress -> {
                    Log.e("loading", "handleUiState: " + it.loading)


                }

                is Resource.Success -> {
                    Log.e("success", "handleUiState: " + it.data.problems.toString())
                    produce(HomeActions.Success(it.data))

                }
            }
        }
    }
    }
