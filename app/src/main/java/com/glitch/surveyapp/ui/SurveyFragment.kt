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
import com.glitch.surveyapp.databinding.FragmentSurveyBinding

class SurveyFragment : Fragment() {
    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!
    private var q1 = false
    private var q2 = false
    private var q3 = false

    private val args by navArgs<SurveyFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.surveyBtnFinish.isEnabled = q1 && q2 && q3
        binding.surveyTvName.text = buildString {
            append(getString(R.string.hello))
            append(" ")
            append(args.person.name)
        }
        binding.surveyBtnFinish.setOnClickListener {
            val person = Person(
                args.person.name, args.person.phoneNumber, args.person.eMail, args.person.city,
                binding.surveyEtxtQ1.text.toString(),
                binding.surveyEtxtQ2.text.toString(),
                binding.surveyEtxtQ3.text.toString()
            )
            val action = SurveyFragmentDirections.actionSurveyFragmentToResultFragment(person)
            findNavController().navigate(action)
        }

        binding.surveyEtxtQ1.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 12) {
                binding.surveyTxtfieldQ1.error = getString(R.string.too_long)
                q1 = false
            } else if (text.isEmpty()) {
                binding.surveyTxtfieldQ1.error = getString(R.string.empty_text)
                q1 = false
            } else {
                binding.surveyTxtfieldQ1.error = null
                q1 = true
            }
            binding.surveyBtnFinish.isEnabled = q1 && q2 && q3
        }

        binding.surveyEtxtQ2.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 7) {
                binding.surveyTxtfieldQ2.error = getString(R.string.too_long)
                q2 = false
            } else if (text.isEmpty()) {
                binding.surveyTxtfieldQ2.error = getString(R.string.empty_text)
                q2 = false
            } else {
                binding.surveyTxtfieldQ2.error = null
                q2 = true
            }
            binding.surveyBtnFinish.isEnabled = q1 && q2 && q3
        }

        binding.surveyEtxtQ3.doOnTextChanged { text, _, _, _ ->
            if (text!!.length > 8) {
                binding.surveyTxtfieldQ3.error = getString(R.string.too_long)
                q3 = false
            } else if (text.isEmpty()) {
                binding.surveyTxtfieldQ3.error = getString(R.string.empty_text)
                q3 = false
            } else {
                binding.surveyTxtfieldQ3.error = null
                q3 = true
            }
            binding.surveyBtnFinish.isEnabled = q1 && q2 && q3
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}