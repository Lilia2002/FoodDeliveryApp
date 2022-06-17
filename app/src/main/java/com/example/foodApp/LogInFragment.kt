package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInFragment : Fragment() {
    lateinit var binding: FragmentLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater)
       return binding.root
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()



        binding.tvSignup.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegistrationFragment())
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.progressBar.visibility=View.VISIBLE
       findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment())
                    } else {
                        Toast.makeText(context,
                            it.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            } else {
                Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT
                ).show()

            }

        }

        binding.loginForgotPasswordTv.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_forgetPasswordFragment)
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){

        }

    }


}