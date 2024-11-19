package com.agrospread.agrospread.agrospread.Model

data class OrderDetailsModel(
    val product: String = "N/A",
    val name: String = "N/A",
    val address: String = "N/A",
    val paymentMethod: String = "N/A",
    val phoneNumber: String = "N/A",
    val shipping: Int = 135,
    val totalPrice: Int = 0
) {
    val totalPriceWithShipping: Int
        get() = totalPrice + shipping
}