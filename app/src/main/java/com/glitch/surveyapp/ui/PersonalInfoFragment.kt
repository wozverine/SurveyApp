package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.surveyapp.R
import com.glitch.surveyapp.data.model.Person
import com.glitch.surveyapp.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {

    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private var phoneNumberEntered = false
    private var mailEntered = false
    private var countyEntered = false

    private val args by navArgs<PersonalInfoFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.personalEdittextName.text = buildString{
            append(getString(R.string.hello))
            append(" ")
            append(args.person.name)
        }
        binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        binding.buttonStartSurvey.setOnClickListener {
            val person = Person(args.person.name,binding.personalInfoPhoneNumberEdit.text.toString().toLong(),
                binding.personalInfoMailEdit.text.toString(),binding.personalInfoCityEdit.text.toString(),
                "","","")
            val action = PersonalInfoFragmentDirections.actionPersonalInfoFragmentToSurveyFragment(person)
            findNavController().navigate(action)
        }

        binding.personalInfoPhoneNumberEdit.doOnTextChanged { text, _, _, _ ->
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
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }

        binding.personalInfoMailEdit.doOnTextChanged { text, _, _, _ ->
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
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }

        binding.personalInfoCityEdit.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 15) {
                binding.personalInfoCountryInput.error = getString(R.string.too_long)
                countyEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoCountryInput.error = getString(R.string.empty_text)
                countyEntered = false
            } else {
                binding.personalInfoCountryInput.error = null
                countyEntered = true
            }
            binding.buttonStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}