package com.example.androidtask.ui.fragments

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.androidtask.R
import com.example.androidtask.android.base.android.BaseFragment
import com.example.androidtask.android.extentions.gone
import com.example.androidtask.android.extentions.navigateSafe
import com.example.androidtask.android.extentions.observe
import com.example.androidtask.android.extentions.visible
import com.example.androidtask.databinding.FragmentRegisterBinding
import com.example.androidtask.ui.viewmodel.RegisterActions
import com.example.androidtask.ui.viewmodel.RegisterViewModel
import com.example.data.local.models.RegisterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    override fun onFragmentReady() {
        binding.spinKit.visible()
        binding.fragmentRegisterRegisterBtn.setOnClickListener {
            binding.spinKit.visible()
            mViewModel.insertUser(
                RegisterModel(
                    binding.fragmentRegisterInputTextLayoutName.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutEmail.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutMobilePhone.editText?.text.toString(),
                    binding.fragmentRegisterInputTextLayoutPassword.editText?.text.toString()
                )
            )
        }
        binding.fragmentRegisterLogInTxt.setOnClickListener {
            navigateSafe(R.id.action_registerFragment_to_loginFragment, container = R.id.frag_host)
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
                navigateSafe(
                    R.id.action_registerFragment_to_loginFragment,
                    container = R.id.frag_host
                )
            }

            is RegisterActions.Failure -> {
                Log.e("hazem", "handleUiState: " + action.FailMassage)
            }

            RegisterActions.Loading -> {
                binding.spinKit.gone()
            }
        }
    }

}