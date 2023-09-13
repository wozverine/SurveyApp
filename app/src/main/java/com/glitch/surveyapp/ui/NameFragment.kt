package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.glitch.surveyapp.R
import androidx.navigation.fragment.navArgs
import com.glitch.surveyapp.databinding.FragmentNameBinding
import com.glitch.surveyapp.data.model.Person

class NameFragment : Fragment() {

    private var _binding: FragmentNameBinding? = null
    private val binding get() = _binding!!
    private var nameEntered = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextToPersonal.isEnabled = false
        binding.nextToPersonal.setOnClickListener {
            val person = Person(binding.nameEditText.text.toString(),0,"","","","")
            val action = NameFragmentDirections.actionNameFragmentToPersonalInfoFragment(person)
            findNavController().navigate(action)
            //findNavController().navigate(R.id.action_NameFragment_to_PersonalInfoFragment)
        }

        binding.nameEditText.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 20) {
                binding.inputLayoutMain.error = getString(R.string.too_long)
                nameEntered = false
            } else if (text.isEmpty()) {
                nameEntered = false
                binding.inputLayoutMain.error = getString(R.string.empty_text)
            } else {
                binding.inputLayoutMain.error = null
                nameEntered = true
            }
            binding.nextToPersonal.isEnabled = nameEntered
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}