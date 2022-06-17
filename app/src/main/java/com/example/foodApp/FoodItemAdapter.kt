package com.example.foodApp

import android.graphics.ColorSpace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodApp.DataList.foodArrayList
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class FoodItemAdapter(private val menuList: ArrayList<ModelMenu>) :
    RecyclerView.Adapter<FoodItemAdapter.MyViewHolder>(),Filterable {

    var onItemClick:((ModelMenu)-> Unit)?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodItemAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_menu,
            parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodItemAdapter.MyViewHolder, position: Int) {
        val food: ModelMenu = menuList[position]
        holder.itemName.text = food.itemName
        holder.price.text = food.price.toString()
//        holder.quantity.text = food.quantity.toString()
        holder.star.text = food.star.toString()
        Picasso.get().load(food.image).into(holder.itemImage)
        holder.itemQuantityIncreaseIV.setOnClickListener{
            onItemClick?.invoke(food)


        }


    }

    override fun getItemCount(): Int {
        return menuList.size
        fun filterList(filteredList: ArrayList<ModelMenu>) {
            menuList = filteredList
            notifyDataSetChanged()
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val price: TextView = itemView.findViewById(R.id.item_price)
        val star: TextView = itemView.findViewById(R.id.item_stars)
//        val quantity: TextView = itemView.findViewById(R.id.item_quantity_tv)
        val itemImage = itemView.findViewById<ImageView>(R.id.item_image)
        val itemQuantityIncreaseIV: ImageView =
            itemView.findViewById(R.id.increase_item_quantity_iv)

    }

//    private val foodFilter = object : Filter() {
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            val filteredList: ArrayList<ModelMenu> = ArrayList()
//            if (constraint == null || constraint.isEmpty()) {
//                menuList.let { filteredList.addAll(it) }
//            } else {
//                val query = constraint.toString().trim().toLowerCase()
//                menuList.forEach {
//                    if (it.itemName?.toLowerCase(Locale.ROOT)!!.contains(query)) {
//                        filteredList.add(it)
//                    }
//                }
//            }
//            val results = FilterResults()
//            results.values = filteredList
//            return results
//        }
//
//        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//            if (results?.values is ArrayList<*>) {
//                menuList.clear()
//                menuList.addAll(results.values as ArrayList<ModelMenu>)
//                notifyDataSetChanged()
//            }
//        }
//    }
// override fun getFilter(): Filter {
//        return foodFilter
//    }

    override fun getFilter(): Filter {
        return searchFilter;
    }
    private val searchFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<ModelMenu>()
            if (constraint!!.isEmpty()) {
                filteredList.addAll(foodArrayList)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                for (item in foodArrayList) {
                    if (item.itemName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }

                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            menuList.clear()
           menuList.addAll(results!!.values as ArrayList<ModelMenu>)
            notifyDataSetChanged()
        }
    }

}