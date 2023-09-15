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
        binding.buttonFinishSurvey.isEnabled = q1 && q2 && q3
        binding.textviewNameSurveyFragment.text = buildString {
            append(getString(R.string.hello))
            append("")
            append(args.person.name)
        }
        binding.buttonFinishSurvey.setOnClickListener {
            val person = Person(args.person.name,args.person.phoneNumber, args.person.eMail, args.person.city,
                binding.textEditQuestion1SurveyFragment.text.toString(),
                binding.textEditQuestion2SurveyFragment.text.toString(),
                binding.textEditQuestion3SurveyFragment.text.toString())
            val action = SurveyFragmentDirections.actionSurveyFragmentToResultFragment(person)
            findNavController().navigate(action)
        }

        binding.textEditQuestion1SurveyFragment.doOnTextChanged { text, _, _, _ ->
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

        binding.textEditQuestion2SurveyFragment.doOnTextChanged { text, _, _, _ ->
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

        binding.textEditQuestion3SurveyFragment.doOnTextChanged { text, _, _, _ ->
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