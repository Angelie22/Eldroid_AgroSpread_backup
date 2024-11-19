package com.agrospread.agrospread.agrospread.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.ViewModel.OrderDetailsViewModel

class   OderDetails : AppCompatActivity() {

    private val viewModel: OrderDetailsViewModel by viewModels()

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oder_details)

        val productTextView: TextView = findViewById(R.id.textViewOrderName)
        val nameTextView: TextView = findViewById(R.id.textViewName)
        val addressTextView: TextView = findViewById(R.id.textViewAddress)
        val paymentMethodTextView: TextView = findViewById(R.id.textViewPaymentMethod)
        val phoneNumberTextView: TextView = findViewById(R.id.textViewPhoneNumber)
        val shippingTextView: TextView = findViewById(R.id.textViewShipping)
        val totalPriceTextView: TextView = findViewById(R.id.textViewTotalPrice)

        // Observe the ViewModel
        viewModel.orderDetails.observe(this, Observer { orderDetails ->
            productTextView.text = getString(R.string.product_name, orderDetails.product)
            nameTextView.text = getString(R.string.order_details_name, orderDetails.name)
            addressTextView.text = getString(R.string.order_details_address, orderDetails.address)
            paymentMethodTextView.text = getString(R.string.order_details_payment_method, orderDetails.paymentMethod)
            phoneNumberTextView.text = getString(R.string.order_details_phone_number, orderDetails.phoneNumber)
            shippingTextView.text = getString(R.string.order_details_shipping, orderDetails.shipping)
            totalPriceTextView.text = getString(R.string.order_details_total_price, orderDetails.totalPriceWithShipping)
        })

        // Load the data into ViewModel
        viewModel.loadOrderDetails(this)
    }
}