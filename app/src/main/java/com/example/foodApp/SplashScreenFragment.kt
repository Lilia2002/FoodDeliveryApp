package com.example.foodApp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash_screen,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Default) {
            timer()
        }

        val db = Firebase.firestore
        db.collection("food")
            .get()
            .addOnSuccessListener { result ->
                CoroutineScope(Dispatchers.Main).launch {
                    for (document in result) {
                        DataList.foodArrayList.add(
                            ModelMenu(
                                document["image"].toString(),
                                document["itemName"].toString(),
                                document["price"].toString(),
                                document["quantity"].toString(),
                                document["star"].toString()
                            )
                        )
                    }
                }

            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }



                .addOnFailureListener { exception ->
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }

        }


    private suspend fun timer() {
        delay(3000)
        withContext(Dispatchers.Main) {
            findNavController().navigate(R.id.action_splashScreeFragment_to_intro1Fragment)
        }
    }
}


