package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.glitch.surveyapp.R
import com.glitch.surveyapp.databinding.FragmentSurveyBinding

class SurveyFragment : Fragment() {
    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!
    private var q1 = false
    private var q2 = false
    private var q3 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFinishSurvey.isEnabled = q1 && q2 && q3
        binding.buttonFinishSurvey.setOnClickListener {
            findNavController().navigate(R.id.action_SurveyFragment_to_ResultFragment)
        }

        binding.textEditQuestion1SurveyFragment.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 12){
                binding.textInputQuestion1SurveyFragment.error = getString(R.string.too_long)
                q1 = false
            } else if (text.isEmpty()){
                q1 = false
            } else {
                binding.textInputQuestion1SurveyFragment.error = null
                q1 = true
            }
            binding.buttonFinishSurvey.isEnabled = q1 && q2 && q3
        }

        binding.textEditQuestion2SurveyFragment.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 7){
                binding.textInputQuestion2SurveyFragment.error = getString(R.string.too_long)
                q2 = false
            } else if (text.isEmpty()){
                q2 = false
            } else {
                binding.textInputQuestion2SurveyFragment.error = null
                q2 = true
            }
            binding.buttonFinishSurvey.isEnabled = q1 && q2 && q3
        }

        binding.textEditQuestion3SurveyFragment.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 8){
                binding.textInputQuestion3SurveyFragment.error = getString(R.string.too_long)
                q3 = false
            } else if (text.isEmpty()){
                q3 = false
            } else {
                binding.textInputQuestion3SurveyFragment.error = null
                q3 = true
            }
            binding.buttonFinishSurvey.isEnabled = q1 && q2 && q3
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}