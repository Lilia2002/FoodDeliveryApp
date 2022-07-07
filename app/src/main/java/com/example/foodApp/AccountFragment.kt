package com.example.foodApp

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.foodApp.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.lang.String.format
import java.net.URI
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    lateinit var imageUri: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAccountBinding.inflate(layoutInflater)
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
                            binding.accountName.text = document.get("name").toString()
                            binding.accountEmail.text = document.get("email").toString()


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
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readDataFirestore()
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_homeFragment_to_logInFragment)
        }
        binding.profileUserIcon.setOnClickListener {
            selectImage()

        }
        binding.tvSave.setOnClickListener {
            binding.progressBar1.visibility = View.VISIBLE
            upLoudImage()

        }

            FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
                .addOnSuccessListener {
                    val imageURL=it.data?.get("imageURL").toString()
                    Picasso.get().load(imageURL).into(binding.profileUserIcon)
                }


    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    private fun upLoudImage() {
        binding.progressBar1.visibility = View.VISIBLE
        val formatter = SimpleDateFormat("yyy", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName*.png")
        storageReference.putFile(imageUri).addOnSuccessListener {
            binding.profileUserIcon.setImageURI(null)
            Toast.makeText(context, "Successfully uplouded", Toast.LENGTH_SHORT).show()
            storageReference.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    val photoURL = it.result.toString()
                    val updateHashMap = hashMapOf<String, Any>(
                        "imageURL" to photoURL
                    )
                    FirebaseFirestore.getInstance().collection("users")
                        .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                        .update(updateHashMap).addOnSuccessListener {
                            binding.progressBar1.visibility = View.INVISIBLE
                        }
                }
            }
        }.addOnFailureListener {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null && data.data != null) {
            if (resultCode == RESULT_OK) {
                imageUri = data?.data!!
                binding.profileUserIcon.setImageURI(imageUri)
            }

        }
    }
}