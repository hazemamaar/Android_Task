package com.example.androidtask.ui.fragments

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.databinding.FragmentHomePageBinding
import com.example.androidtask.ui.adapters.MedicationListAdapter
import com.example.androidtask.ui.viewmodel.HomeActions
import com.example.androidtask.ui.viewmodel.HomeViewModel
import com.example.core.base.android.BaseFragment
import com.example.core.extentions.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding,HomeViewModel>() {
    @Inject
    lateinit var medicationListAdapter: MedicationListAdapter

    override fun onFragmentReady() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


        val greetingMessage = when (currentTime()) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }

        binding.greetingTextView.text = greetingMessage
        mViewModel.getProblems()
        subscribeToObservers()
    }

    override val mViewModel: HomeViewModel by viewModels()

    private fun currentTime() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    private fun subscribeToObservers() {
        mViewModel.apply {
            observe(mViewModel.viewState) {
                handleUiState(it)
            }
        }
    }
    private fun handleUiState(action: HomeActions) {
        when (action) {
            is HomeActions.Success -> {
                Log.e("hhaz", "handleUiState:${action.responseObject.problems[0].Diabetes[0].medications[0].medicationsClasses[0].className[0].associatedDrug} ", )
                binding.recyclerView.apply {
                    medicationListAdapter.medicationList = action.responseObject.problems[0].Diabetes[0].medications[0].medicationsClasses[0].className[0].associatedDrug
                    adapter = medicationListAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }

            is HomeActions.Failure ->{

            }
            HomeActions.Loading -> {
            }
        }
    }
}