package com.example.androidtask.ui.fragments

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.androidtask.databinding.FragmentLoginBinding
import com.example.androidtask.ui.viewmodel.LoginActions
import com.example.androidtask.ui.viewmodel.LoginViewModel
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.gone
import com.example.core.extentions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun onFragmentReady() {
        binding.signinBtn.setOnClickListener {
            mViewModel.getUser(binding.inputTextLayoutEmail.editText?.text.toString())
        }
        subscribeToObservers()
    }

    override val mViewModel: LoginViewModel by viewModels()

    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(action: LoginActions) {
        when (action) {
            is LoginActions.Success -> {

            }

            is LoginActions.Failure ->{

            }
            LoginActions.Loading -> {
                binding.spinKit.gone()
            }
        }
    }

}