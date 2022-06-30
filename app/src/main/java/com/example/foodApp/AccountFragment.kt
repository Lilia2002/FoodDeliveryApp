package com.example.foodApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.foodApp.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountFragment : Fragment() {
    private lateinit var  binding: FragmentAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAccountBinding.inflate(layoutInflater)
        return binding.root
    }
    private fun readDataFirestore() {
        val db = Firebase.firestore
        Firebase.auth.currentUser?.let {
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    var usersInfo: UserInfo
                    for (document in result) {
                        if ((document.get("idUser")
                                .toString()) == (FirebaseAuth.getInstance().uid).toString()
                        ) {
                            binding.accountName.text =  document.get("name").toString()
                            binding.accountEmail.text=document.get("email").toString()



                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(
                        "TAG",
                        "Error getting documents.",
                        exception
                    )
                }
//            try{
//                Glide.with(this@AccountFragment).load(profileImage).placeholder(R.drawable.user_female).into(binding.profileUserIcon)
//            }
//            catch (e: Exception){
//
//            }

    }



}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readDataFirestore()
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
        }
    }
}