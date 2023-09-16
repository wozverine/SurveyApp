package com.glitch.surveyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.surveyapp.R
import com.glitch.surveyapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.resultTvName.text = args.person.name
        binding.resultTvPhone.text = buildString {
            append(getString(R.string.phone_number))
            appendLine()
            append(args.person.phoneNumber.toString())
        }
        binding.resultTvCity.text = buildString {
            append(getString(R.string.city))
            appendLine()
            append(args.person.city)
        }
        binding.resultTvEmail.text = buildString {
            append(getString(R.string.email))
            appendLine()
            append(args.person.eMail)
        }
        binding.resultTvQ1.text = buildString {
            append(getString(R.string.question_1))
            appendLine()
            append(args.person.answer1)
        }
        binding.resultTvQ2.text = buildString {
            append(getString(R.string.question_2))
            appendLine()
            append(args.person.answer2)
        }
        binding.resultTvQ3.text = buildString {
            append(getString(R.string.question_3))
            appendLine()
            append(args.person.answer3)
        }
        binding.resultBtnExit.setOnClickListener {
            findNavController().popBackStack(R.id.NameFragment, false)
        }
        binding.resultBtnRetry.setOnClickListener {
            findNavController().popBackStack(R.id.NameFragment, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}