package com.example.foodApp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.foodApp.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrationFragment : Fragment() {
    lateinit var binding: FragmentRegistrationBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_logInFragment)
        }
        binding.btnRegister.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            binding.progressBar.visibility=View.VISIBLE
                            createNewUser()
                            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(context, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun createNewUser() {
        val db = Firebase.firestore
        val user = hashMapOf(
            "name" to binding.nameEt.text.toString(),
            "email" to binding.emailEt.text.toString(),
            "idUser" to firebaseAuth.uid.toString(),
             "imageURL" to ""
        )

        db.collection("users").document(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .set(user)
            .addOnSuccessListener { Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: ") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Error adding document", e) }
    }

}