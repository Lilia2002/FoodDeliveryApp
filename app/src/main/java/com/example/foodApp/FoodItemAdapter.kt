package com.example.foodApp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class FoodItemAdapter(private var menuList: ArrayList<ModelMenu>) :
    RecyclerView.Adapter<FoodItemAdapter.MyViewHolder>() {

    var onItemClick:((ModelMenu)-> Unit)?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_menu,
            parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val food: ModelMenu = menuList[position]
        holder.itemName.text = food.itemName
        holder.price.text = food.price.toString()
//        holder.quantity.text = food.quantity.toString()
        holder.star.text = food.star.toString()
        Picasso.get().load(food.image).into(holder.itemImage)

        holder.itemQuantityIncreaseIV.setOnClickListener{
            onItemClick?.invoke(food)
        }
        var n =0
        holder.decrease.setOnClickListener {
            if(n!=0)
           n--
            holder.itemQuantity.text=n.toString()
            onItemClick?.invoke(food)
        }
        holder.itemQuantityIncreaseIV.setOnClickListener {
            n++
          holder.itemQuantity.text=n.toString()
        }

        food.quantity = n.toString()
    }
    override fun getItemCount(): Int=menuList.size

    fun updateItems(arrayList: java.util.ArrayList<ModelMenu>) {
        menuList.clear()
        menuList.addAll(arrayList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val star: TextView = itemView.findViewById(R.id.item_stars)
//        val quantity: TextView = itemView.findViewById(R.id.item_quantity_tv)
        val itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        val itemQuantityIncreaseIV: ImageView =
            itemView.findViewById(R.id.increase_item_quantity_iv)
        val decrease:ImageView =itemView.findViewById(R.id.decrease_item_quantity_iv)
        val itemQuantity= itemView.findViewById<TextView>(R.id.item_quantity_tv)
    }
}