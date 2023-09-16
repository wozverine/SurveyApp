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
        binding.personalInfoTxtName.text = buildString {
            append(getString(R.string.hello))
            append(" ")
            append(args.person.name)
        }
        binding.personalInfoBtnStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        binding.personalInfoBtnStartSurvey.setOnClickListener {
            val person = Person(
                args.person.name,
                binding.personalInfoEtxtPhone.text.toString().toLong(),
                binding.personalInfoEtxtMail.text.toString(),
                binding.personalInfoEtxtCity.text.toString(),
                "",
                "",
                ""
            )
            val action =
                PersonalInfoFragmentDirections.actionPersonalInfoFragmentToSurveyFragment(person)
            findNavController().navigate(action)
        }

        binding.personalInfoEtxtPhone.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 13) {
                binding.personalInfoTxtfieldPhone.error = getString(R.string.too_long)
                phoneNumberEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoTxtfieldPhone.error = getString(R.string.empty_text)
                phoneNumberEntered = false
            } else {
                binding.personalInfoTxtfieldPhone.error = null
                phoneNumberEntered = true
            }
            binding.personalInfoBtnStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }

        binding.personalInfoEtxtMail.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 25) {
                binding.personalInfoTxtfieldMail.error = getString(R.string.too_long)
                mailEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoTxtfieldMail.error = getString(R.string.empty_text)
                mailEntered = false
            } else {
                binding.personalInfoTxtfieldMail.error = null
                mailEntered = true
            }
            binding.personalInfoBtnStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }

        binding.personalInfoEtxtCity.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 15) {
                binding.personalInfoTxtfieldCity.error = getString(R.string.too_long)
                countyEntered = false
            } else if (text.isEmpty()) {
                binding.personalInfoTxtfieldCity.error = getString(R.string.empty_text)
                countyEntered = false
            } else {
                binding.personalInfoTxtfieldCity.error = null
                countyEntered = true
            }
            binding.personalInfoBtnStartSurvey.isEnabled = phoneNumberEntered && mailEntered && countyEntered
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}