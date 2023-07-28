package com.example.dynamicdelivery.ui.module4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dynamicdelivery.R
import com.example.dynamicdelivery.databinding.FragmentScreen1Binding

class Screen1Fragment : Fragment() {

    private var binding: FragmentScreen1Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScreen1Binding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonNext?.setOnClickListener {
            findNavController().navigate(R.id.action_screen1Fragment_to_screen2Fragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}