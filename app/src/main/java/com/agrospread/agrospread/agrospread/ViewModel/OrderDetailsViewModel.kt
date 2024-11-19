package com.agrospread.agrospread.agrospread.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.OrderDetailsModel

class OrderDetailsViewModel : ViewModel() {

    private val _orderDetails = MutableLiveData<OrderDetailsModel>()
    val orderDetails: LiveData<OrderDetailsModel> get() = _orderDetails

    fun loadOrderDetails(context: Context) {
        val sharedPreferences = context.getSharedPreferences("CheckoutInfo", Context.MODE_PRIVATE)
        val product = sharedPreferences.getString("Product", "N/A") ?: "N/A"
        val name = sharedPreferences.getString("name", "N/A") ?: "N/A"
        val address = sharedPreferences.getString("address", "N/A") ?: "N/A"
        val paymentMethod = sharedPreferences.getString("paymentMethod", "N/A") ?: "N/A"
        val phoneNumber = sharedPreferences.getString("phoneNumber", "N/A") ?: "N/A"
        val shipping = sharedPreferences.getInt("shipping", 135)
        val totalPrice = sharedPreferences.getInt("totalPrice", 0)

        _orderDetails.value = OrderDetailsModel(
            product = product,
            name = name,
            address = address,
            paymentMethod = paymentMethod,
            phoneNumber = phoneNumber,
            shipping = shipping,
            totalPrice = totalPrice
        )
    }
}