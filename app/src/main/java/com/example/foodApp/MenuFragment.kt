package com.example.foodApp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MenuFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: FoodItemAdapter
    private lateinit var searchBox: SearchView
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


