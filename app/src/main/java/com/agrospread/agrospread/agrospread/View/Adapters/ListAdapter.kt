package com.agrospread.agrospread.agrospread.View.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.ListData

class ListAdapter(
    context: Context,
    private val dataArrayList: List<ListData>,
    private val addToCart: (ListData) -> Unit,
    private val addToFavorites: (ListData) -> Unit
) : ArrayAdapter<ListData>(context, 0, dataArrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val listData = getItem(position)

        // Find views
        val itemName = view.findViewById<TextView>(R.id.name)
        val itemImage = view.findViewById<ImageView>(R.id.listImage1)
        val itemPrice = view.findViewById<TextView>(R.id.price)
        val btnAddToCart = view.findViewById<ImageView>(R.id.addToCartIcon)
        val btnAddToFavorites = view.findViewById<ImageView>(R.id.favoriteIcon)

        // Bind data to views
        itemName.text = listData?.name
        listData?.image?.let { itemImage.setImageResource(it) }
        itemPrice.text = listData?.price

        // Set onClickListeners
        btnAddToCart.setOnClickListener {
            listData?.let { addToCart(it) }
        }

        btnAddToFavorites.setOnClickListener {
            listData?.let { addToFavorites(it) }
        }

        return view
    }
}
