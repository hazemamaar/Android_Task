package com.example.androidtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtask.R
import com.example.androidtask.databinding.FragmentHomePageBinding
import com.example.androidtask.ui.viewmodel.HomeViewModel
import com.example.core.base.android.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding,HomeViewModel>() {


    override fun onFragmentReady() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


    val greetingMessage = when (currentTime()) {
        in 0..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        else -> "Good Evening"
    }

    binding.greetingTextView.text = greetingMessage
    }

    override val mViewModel: HomeViewModel
        get() = TODO("Not yet implemented")

    private fun currentTime() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

}