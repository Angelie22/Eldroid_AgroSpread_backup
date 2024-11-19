package com.agrospread.agrospread.agrospread.View.Adapters

import com.agrospread.agrospread.agrospread.Model.ListData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R

class FavoriteAdapter(
    private val context: Context,
    private val favItems: ArrayList<ListData>?,
    private val removeItem: (Int) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.name)
        val itemImage: ImageView = itemView.findViewById(R.id.listImage1)
        val itemPrice: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val listData = favItems?.get(position)
        if (listData != null) {
            holder.itemName.text = listData.name
        }
        if (listData != null) {
            holder.itemImage.setImageResource(listData.image)
        }
        if (listData != null) {
            holder.itemPrice.text = listData.price
        }
    }

    override fun getItemCount(): Int = favItems?.size!!

    fun removeItemAt(position: Int) {
        favItems?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItemAt(position: Int, item: ListData) {
        favItems?.add(position, item)
        notifyItemInserted(position)
    }
}