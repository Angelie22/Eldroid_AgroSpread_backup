package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.ListData

class AllActivityViewModel : ViewModel() {

    val cartItems = MutableLiveData<ArrayList<ListData>>()
    val favItems = MutableLiveData<ArrayList<ListData>>()

    init {
        cartItems.value = ArrayList()
        favItems.value = ArrayList()
    }

    fun addToCart(item: ListData) {
        cartItems.value?.let {
            if (!it.contains(item)) {
                it.add(item)
                cartItems.value = it
            }
        }
    }

    fun addToFavorites(item: ListData) {
        favItems.value?.let {
            if (!it.contains(item)) {
                it.add(item)
                favItems.value = it
            }
        }
    }
}
