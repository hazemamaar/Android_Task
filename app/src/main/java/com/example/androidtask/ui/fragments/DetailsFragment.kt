package com.example.androidtask.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.androidtask.R
import com.example.androidtask.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var _binding:FragmentDetailsBinding
    private val binding get() = _binding

    private val args: DetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTextView.text = args.medication.name
        binding.strengthTextView.text = args.medication.strength
        binding.descriptionTextView.text = args.medication.description
        binding.activeIngredientTextView.text = args.medication.active_ingredient
        binding.dosageInstructionsTextView.text = args.medication.dosage_instructions
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

}