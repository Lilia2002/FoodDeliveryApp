package com.example.foodApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.collections.contains as contains

class MenuFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var foodArrayList: MutableList<ModelMenu>
    private lateinit var myAdapter: FoodItemAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var searchBox: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.items_recycler_view)
        searchBox = view.findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        foodArrayList = mutableListOf<ModelMenu>()
        myAdapter = FoodItemAdapter(foodArrayList as ArrayList<ModelMenu>)
        recyclerView.adapter = myAdapter

//        EventChangeListener()
        recyclerView.layoutManager = LinearLayoutManager(context)
        myAdapter = FoodItemAdapter(DataList.foodArrayList as ArrayList<ModelMenu>)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
        myAdapter.onItemClick={
            DataList.ordersList.add(
                ModelMenu(
                    it.image,
                    it.itemName,
                    it.price,
                    it.quantity,
                    it.star
            )
            )
        }
        searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                myAdapter.filter.filter(p0)
                return false
            }
        })
    }
//        searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//            androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                myAdapter.getFilter().filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                myAdapter.getFilter().filter(newText);
//                return false
//            }
//        })

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("food").orderBy("itemName.Query.Direction.ASCENDING")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {

                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return

                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
//                        foodArrayList.add(dc.document.toObject(ModelMenu::class.java))


                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })


    }

}


