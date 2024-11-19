package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.ListData

class FertilizersViewModel : ViewModel() {

    private val dataArrayList = ArrayList<ListData>()
    private val cartItems = ArrayList<ListData>()
    private val favItems = ArrayList<ListData>()

    fun setData(data: List<ListData>) {
        dataArrayList.clear()
        dataArrayList.addAll(data)
    }

    fun getData(): List<ListData> = dataArrayList

    fun addItemToCart(item: ListData): Boolean {
        return if (!cartItems.contains(item)) {
            cartItems.add(item)
            true
        } else {
            false
        }
    }

    fun addItemToFavorites(item: ListData): Boolean {
        return if (!favItems.contains(item)) {
            favItems.add(item)
            true
        } else {
            false
        }
    }

    fun getCartItems(): List<ListData> = cartItems

    fun getFavoriteItems(): List<ListData> = favItems

    fun addToCart(items: List<ListData>) {
        cartItems.clear()
        cartItems.addAll(items)
    }

    fun addToFavorites(items: List<ListData>) {
        favItems.clear()
        favItems.addAll(items)
    }
}
