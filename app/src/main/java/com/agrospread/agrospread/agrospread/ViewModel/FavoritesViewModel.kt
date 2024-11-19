package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.ListData

class FavoritesViewModel : ViewModel() {

    private val _favItems = MutableLiveData<ArrayList<ListData>?>()
    val favItems: MutableLiveData<ArrayList<ListData>?> get() = _favItems

    init {
        _favItems.value = ArrayList()  // Initialize with an empty list or fetch from repository
    }

    fun setFavItems(items: ArrayList<ListData>) {
        _favItems.value = items
    }

    fun removeItem(position: Int) {
        val updatedItems = _favItems.value?.apply { removeAt(position) }
        _favItems.value = updatedItems
    }

    fun addItem(position: Int, item: ListData) {
        val updatedItems = _favItems.value?.apply { add(position, item) }
        _favItems.value = updatedItems
    }
}
