package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding.buttonRetry.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_NameFragment)
        }

        binding.resultTvName.text = args.person.name
        binding.resultTvPhone.text = buildString {
            append(getString(R.string.phone_number))
            append(System.getProperty("line.separator"))
            append(args.person.phoneNumber.toString())
        }
        binding.resultTvCity.text = buildString {
            append(getString(R.string.city))
            append(System.getProperty("line.separator"))
            append(args.person.city)
        }
        binding.resultTvEmail.text = buildString {
            append(getString(R.string.email))
            append(System.getProperty("line.separator"))
            append(args.person.eMail)
        }
        binding.resultTvQ1.text = buildString {
            append(getString(R.string.question_1))
            append(System.getProperty("line.separator"))
            append(args.person.answer1)
        }
        binding.resultTvQ2.text = buildString {
            append(getString(R.string.question_2))
            append(System.getProperty("line.separator"))
            append(args.person.answer2)
        }
        binding.resultTvQ3.text = buildString {
            append(getString(R.string.question_3))
            append(System.getProperty("line.separator"))
            append(args.person.answer3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}