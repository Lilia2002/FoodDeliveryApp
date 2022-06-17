package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentIntro1Binding
import com.google.firebase.auth.FirebaseAuth


class Intro1Fragment : Fragment() {
    private lateinit var binding: FragmentIntro1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.skip.setOnClickListener {
            findNavController().navigate(R.id.action_intro1Fragment_to_logInFragment)
        }
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_intro1Fragment_to_intro2Fragment)
        }
    }
}