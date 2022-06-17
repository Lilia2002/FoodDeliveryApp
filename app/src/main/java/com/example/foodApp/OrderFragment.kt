package com.example.foodApp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodApp.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
binding = FragmentOrderBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding.apply {
           rvOrder.layoutManager = LinearLayoutManager(context)
           orderAdapter = OrderAdapter(DataList.ordersList)
           rvOrder.adapter = orderAdapter
       }
    }

}