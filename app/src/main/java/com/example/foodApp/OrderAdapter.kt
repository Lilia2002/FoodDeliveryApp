package com.example.foodApp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodApp.databinding.ItemOrderBinding
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class OrderAdapter(
    val orderList: MutableList<ModelMenu>
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemOrderBinding.bind(itemView)
        fun bind(modelMenu: ModelMenu) {
            binding.itemName.text = modelMenu.itemName
            Picasso.get().load(modelMenu.image).into(binding.imgItemImage)
            binding.itemPrice.text = modelMenu.price
            binding.itemStars.text = modelMenu.star

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
    )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orderList[position])
        var p= orderList[position]
        holder.binding.btnDelete.setOnClickListener {
                orderList.removeAt(position)
            notifyDataSetChanged()
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Cancel")
            builder.setMessage("Are sure you want to cancel this order?")
            builder.setPositiveButton("Confirm") { _, _ ->

                    Toast.makeText(holder.itemView.context, "The order is canceled...", Toast.LENGTH_LONG)
                        .show()
//                    deleteOrder(p.itemName, holder)
                }
                .setNegativeButton("Cancel") { a, d ->
                    a.dismiss()
                }
                .show()
        }
    }
        private fun deleteOrder(itemName:String, holder: OrderViewHolder) {
            val ref = FirebaseDatabase.getInstance().getReference("food")
            ref.child(itemName)
                .removeValue()
                .addOnSuccessListener {
                    Toast.makeText(
                        holder.itemView.context,
                        "The order was canceled...",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        holder.itemView.context,
                        "Unable to delete due to ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }

    override fun getItemCount(): Int {
        return orderList.size
    }
}