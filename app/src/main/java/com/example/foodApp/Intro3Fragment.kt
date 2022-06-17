package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentIntro2Binding
import com.example.foodApp.databinding.FragmentIntro3Binding

class Intro3Fragment : Fragment() {
    lateinit var binding: FragmentIntro3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro3Binding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_intro3Fragment_to_logInFragment)
        }
        binding.back2.setOnClickListener {
            findNavController().navigate(R.id.action_intro3Fragment_to_intro2Fragment)
        }
    }
}