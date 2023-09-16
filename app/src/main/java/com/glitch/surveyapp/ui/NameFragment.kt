package com.glitch.surveyapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.glitch.surveyapp.R
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

        binding.nameBtnToPersonal.isEnabled = false
        binding.nameBtnToPersonal.setOnClickListener {
            val person = Person(binding.nameEtxtName.text.toString(), 0, "", "", "", "", "")
            val action = NameFragmentDirections.actionNameFragmentToPersonalInfoFragment(person)
            findNavController().navigate(action)
            //findNavController().navigate(R.id.action_NameFragment_to_PersonalInfoFragment)
        }

        binding.nameEtxtName.doOnTextChanged { text, _, _, _ ->
            with(text) {
                if (this!!.length > 20) {
                    binding.nameTxtfieldName.error = getString(R.string.too_long)
                    nameEntered = false
                } else if (this.isEmpty()) {
                    binding.nameTxtfieldName.error = getString(R.string.empty_text)
                    nameEntered = false
                } else {
                    binding.nameTxtfieldName.error = null
                    nameEntered = true
                }
            }
            binding.nameBtnToPersonal.isEnabled = nameEntered
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}