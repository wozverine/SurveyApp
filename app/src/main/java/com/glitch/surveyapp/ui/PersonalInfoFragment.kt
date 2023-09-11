package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.glitch.surveyapp.R
import com.glitch.surveyapp.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {

    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private var phoneNumberEntered = false
    private var mailEntered = false
    private var counrtyEntered = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && counrtyEntered
        binding.buttonStartSurvey.setOnClickListener {
            findNavController().navigate(R.id.action_PersonalInfoFragment_to_SurveyFragment)
        }

        binding.personalInfoPhoneNumberEdit.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 13) {
                binding.personalInfoPhoneNumberInput.error = getString(R.string.too_long)
                phoneNumberEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoPhoneNumberInput.error = getString(R.string.empty_text)
                phoneNumberEntered = false
            } else {
                binding.personalInfoPhoneNumberInput.error = null
                phoneNumberEntered = true
            }
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && counrtyEntered
        }

        binding.personalInfoMailEdit.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 25) {
                binding.personalInfoMailInput.error = getString(R.string.too_long)
                mailEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoMailInput.error = getString(R.string.empty_text)
                mailEntered = false
            } else {
                binding.personalInfoMailInput.error = null
                mailEntered = true
            }
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && counrtyEntered
        }

        binding.personalInfoCountryEdit.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 15) {
                binding.personalInfoCountryInput.error = getString(R.string.too_long)
                counrtyEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoCountryInput.error = getString(R.string.empty_text)
                counrtyEntered = false
            } else {
                binding.personalInfoCountryInput.error = null
                counrtyEntered = true
            }
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && counrtyEntered
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}