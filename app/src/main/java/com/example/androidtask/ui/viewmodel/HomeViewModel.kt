package com.example.androidtask.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.features.getMedicals.domain.interactors.GetProblemsUseCase
import com.example.androidtask.android.base.android.Action
import com.example.androidtask.android.base.android.BaseViewModel
import com.example.core.response.Resource
import com.example.data.network.models.Medication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class HomeActions() : Action {
    object Loading : HomeActions()
    data class Success(val medical: List<Medication>) : HomeActions()

    data class Failure(val FailMassage: String) : HomeActions()
}

@HiltViewModel
class HomeViewModel @Inject constructor(private val getProblemsUseCase: GetProblemsUseCase) :
    BaseViewModel<HomeActions>() {
    fun getProblems() {
        getProblemsUseCase(viewModelScope) {
            when (it) {
                is Resource.Failure -> {
                }

                is Resource.Progress -> {
                }

                is Resource.Success -> {
                    Log.e("hha", it.data.toString() )
                    produce(HomeActions.Success(it.data.medications))

                }
            }
        }
    }
    }
