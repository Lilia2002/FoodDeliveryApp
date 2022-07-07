package com.example.foodApp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat.recreate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MenuFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: FoodItemAdapter
    private lateinit var searchBox: SearchView
    private lateinit var btnDrink: LinearLayout
    private lateinit var btnPizza : LinearLayout
    private lateinit var btnSoup : LinearLayout
    private lateinit var btnDessert : LinearLayout
    private lateinit var btnFri: LinearLayout
    private lateinit var btnChicken:LinearLayout
    private lateinit var btnBurger:LinearLayout
    private lateinit var btnSalad:LinearLayout
   private lateinit var foodCategoriesContainer:LinearLayout
    private lateinit var showAllSwitch: SwitchCompat
    lateinit var dataList: ArrayList<ModelMenu>
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
        btnDrink = view.findViewById(R.id.btn_drinks)
        btnPizza= view.findViewById(R.id.btn_pizza)
        btnSoup= view.findViewById(R.id.btn_soup)
        foodCategoriesContainer=view.findViewById(R.id.food_categories_container_ll)
        btnChicken=view.findViewById(R.id.btn_chicken)
        btnFri= view.findViewById(R.id.btn_fri)
        showAllSwitch = view.findViewById(R.id.show_all_items_switch)
        btnDessert= view.findViewById(R.id.btn_cake)
        btnBurger= view.findViewById(R.id.btn_burger)
        btnSalad= view.findViewById(R.id.btn_salad)
        dataList = DataList.foodArrayList as ArrayList<ModelMenu>
        recyclerView.layoutManager = LinearLayoutManager(context)

        myAdapter = FoodItemAdapter(ArrayList())
        recyclerView.adapter = myAdapter
        myAdapter.updateItems(dataList)
        myAdapter.onItemClick = {
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
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchFilter.filter(newText)
                return true
            }
        })
        showAllSwitch.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore
            dB.collection("food")
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
                            myAdapter.updateItems(dataList)
                        }
                    }

                }
        }

       btnDrink.setOnClickListener {
         dataList.clear()
           val dB = Firebase.firestore
           dB.collection("Drink")
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
                           myAdapter.updateItems(dataList)
                       }
                   }

               }
       }

        btnPizza.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore
            dB.collection("Pizza")
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
                            myAdapter.updateItems(dataList)
                        }
                    }

                }

        }
        btnSoup.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore
            dB.collection("Soup")
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
                            myAdapter.updateItems(dataList)
                        }
                    }

                }
        }
        btnFri.setOnClickListener {
            dataList.clear()

            val dB = Firebase.firestore
            dB.collection("Fri")
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
                            myAdapter.updateItems(dataList)

                        }
                    }
                }
        }

        btnChicken.setOnClickListener {
            dataList.clear()

            val dB = Firebase.firestore
            dB.collection("Chicken")
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
                            myAdapter.updateItems(dataList)

                        }
                    }
                }
        }
        btnDessert.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore
            dB.collection("Cake")
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
                            myAdapter.updateItems(dataList)

                        }
                    }
                }
        }
        btnDessert.setOnClickListener {
            dataList.clear()

            val dB = Firebase.firestore
            dB.collection("Cake")
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
                            myAdapter.updateItems(dataList)

                        }
                    }
                }
        }
        btnBurger.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore

            dB.collection("Burger")
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
                            myAdapter.updateItems(dataList)
                        }
                    }

                }

        }
        btnSalad.setOnClickListener {
            dataList.clear()
            val dB = Firebase.firestore
            dB.collection("Salad")
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
                            myAdapter.updateItems(dataList)
                        }
                    }

                }

        }
    }

    private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<ModelMenu>()
            if (constraint!!.isEmpty()) {
                filteredList.addAll(dataList)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                for (item in dataList) {
                    if (item.itemName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }
        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            myAdapter.updateItems(results!!.values as ArrayList<ModelMenu>)
        }
    }




}


