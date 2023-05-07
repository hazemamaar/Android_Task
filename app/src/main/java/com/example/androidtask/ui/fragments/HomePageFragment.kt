package com.example.androidtask.ui.fragments

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtask.R
import com.example.androidtask.android.base.android.BaseFragment
import com.example.androidtask.android.extentions.navigateSafe
import com.example.androidtask.android.extentions.observe
import com.example.androidtask.databinding.FragmentHomePageBinding
import com.example.androidtask.ui.adapters.MedicationListAdapter
import com.example.androidtask.ui.viewmodel.HomeActions
import com.example.androidtask.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding, HomeViewModel>() {
    @Inject
    lateinit var medicationListAdapter: MedicationListAdapter
    private val args: HomePageFragmentArgs by navArgs()
    override fun onFragmentReady() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val localtime = when (currentTime()) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }

        binding.localtimeTextView.text = "welcome $localtime"
        binding.greetingTextView.text = args.userModel.name
        mViewModel.getProblems()
        subscribeToObservers()
        medicationListAdapter.setOnItemClickListener {
            navigateSafe(HomePageFragmentDirections.actionHomePageFragmentToDetailsFragment(it), container = R.id.frag_host)
        }
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
                Log.e("xxx", "handleUiState: ${action.medical}", )
                binding.recyclerView.apply {
                    medicationListAdapter.medicationList =
                        action.medical
                    adapter = medicationListAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }

            is HomeActions.Failure -> {

            }

            HomeActions.Loading -> {
            }
        }
    }
}