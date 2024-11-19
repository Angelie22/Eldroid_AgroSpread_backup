package com.agrospread.agrospread.agrospread.View.Adapters

import com.agrospread.agrospread.agrospread.Model.ListData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R

class CartAdapter(
    private val context: Context,
    private val cartItems: ArrayList<ListData>,
    private val cartItemClickListener: CartItemClickListener
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    interface CartItemClickListener {
        fun onQuantityChanged()
    }

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemImage: ImageView = itemView.findViewById(R.id.listImage1)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantityTextView)
        val btnMinus: Button = itemView.findViewById(R.id.btnMinus)
        val btnPlus: Button = itemView.findViewById(R.id.btnPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val listData = cartItems[position]
        holder.itemName.text = listData.name
        holder.itemImage.setImageResource(listData.image)
        holder.itemPrice.text = listData.price
        holder.quantityTextView.text = listData.quantity.toString()

        holder.btnMinus.setOnClickListener {
            if (listData.quantity > 1) {
                listData.quantity -= 1
                holder.quantityTextView.text = listData.quantity.toString()
                cartItemClickListener.onQuantityChanged()
            }
        }

        holder.btnPlus.setOnClickListener {
            listData.quantity += 1
            holder.quantityTextView.text = listData.quantity.toString()
            cartItemClickListener.onQuantityChanged()
        }
    }

    override fun getItemCount(): Int = cartItems.size

    fun removeItemAt(position: Int) {
        cartItems.removeAt(position)
        notifyItemRemoved(position)
    }


    fun addItemAt(position: Int, item: ListData) {
        cartItems.add(position, item)
        notifyItemInserted(position)
    }
}
