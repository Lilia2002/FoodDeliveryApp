package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentIntro2Binding
import com.example.foodApp.databinding.FragmentLogInBinding


class Intro2Fragment : Fragment() {
    lateinit var binding: FragmentIntro2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntro2Binding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next2.setOnClickListener {
            findNavController().navigate(R.id.action_intro2Fragment_to_intro3Fragment)
        }
 binding.back1.setOnClickListener {
    findNavController().navigate(R.id.action_intro2Fragment_to_intro1Fragment)
}
    }

}