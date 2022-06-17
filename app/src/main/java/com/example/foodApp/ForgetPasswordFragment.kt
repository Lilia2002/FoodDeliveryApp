package com.example.foodApp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth


class ForgetPasswordFragment : Fragment() {


    private lateinit var binding : FragmentForgetPasswordBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()


        binding.submitBtn.setOnClickListener {
            validateemail()
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_logInFragment)
        }

    }
    private var email = ""
    private fun validateemail() {
        email = binding.forgetEmailEt.text.toString().trim()
        if (email.isEmpty()) {
            Toast.makeText(this.context, "Type your email", Toast.LENGTH_SHORT).show()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher (email).matches()) {
            Toast.makeText(this.context, "Email is wrong", Toast.LENGTH_SHORT).show()
        }
        else {
            recoverPassword()
        }

    }
    private fun recoverPassword() {

        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {

                Toast.makeText(this.context, "Check your email to reset password\t$email", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this.context, "Failed to send due to${e.message}", Toast.LENGTH_SHORT).show()

            }
    }



}