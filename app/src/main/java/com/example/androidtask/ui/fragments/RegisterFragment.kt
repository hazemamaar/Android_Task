package com.example.androidtask.ui.fragments

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.androidtask.data.local.models.RegisterModel
import com.example.androidtask.databinding.FragmentRegisterBinding
import com.example.androidtask.ui.viewmodel.RegisterActions
import com.example.androidtask.ui.viewmodel.RegisterViewModel
import com.example.core.base.android.BaseFragment
import com.example.core.base.android.BaseViewModel
import com.example.core.extentions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    override fun onFragmentReady() {
        binding.fragmentRegisterRegisterBtn.setOnClickListener {
            mViewModel.insertUser(
                RegisterModel(
                    binding.fragmentRegisterInputTextLayoutName.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutEmail.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutMobilePhone.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutPassword.editText?.text.toString()
                )
            )
        }
        subscribeToObservers()

    }

    override val mViewModel: RegisterViewModel by viewModels()

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: RegisterActions) {
        when (action) {
            is RegisterActions.Success -> {

            }
        }
    }

}