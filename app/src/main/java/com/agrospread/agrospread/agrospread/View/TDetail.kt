package com.agrospread.agrospread.agrospread.View


import android.annotation.SuppressLint
import com.agrospread.agrospread.agrospread.Model.ListData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R
import com.agrospread.agrospread.databinding.ActivityTdetailBinding


class TDetail : AppCompatActivity() {
    private lateinit var binding: ActivityTdetailBinding
    private var quantity: Int = 1
    private var cartItems = ArrayList<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTdetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore saved state
        savedInstanceState?.let {
            cartItems = it.getParcelableArrayList("cartItems") ?: ArrayList()
        }

        // Retrieve passed data from MainActivity
        intent.extras?.let {
            cartItems = it.getParcelableArrayList("cartItems") ?: ArrayList()
        }

        val btnPlus = binding.btnPlus
        val btnMinus = binding.btnMinus
        val quantityTextView = binding.quantityTextView

        // Retrieve data from intent
        val intent = intent
        if (intent != null) {

            val image = intent.getIntExtra("image", R.drawable.t1)


            binding.detailImage.setImageResource(image)

            // Set initial quantity
            quantityTextView.text = quantity.toString()

            binding.addtocartButton.setOnClickListener {
                // Create the ListData object
                val selectedItem = ListData(
                    name = "Shovel for precision farming",
                    image = R.drawable.t1,
                    price = "Price: 199",
                    quantity = quantity
                )

                // Check for duplicates before adding to cart
                addToCart(selectedItem)
            }

            // Set onClickListener for the plus button
            btnPlus.setOnClickListener {
                quantity++
                updateQuantity()
            }

            // Set onClickListener for the minus button
            btnMinus.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                    updateQuantity()
                } else {
                    Toast.makeText(this, "Quantity cannot be negative", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateQuantity() {
        binding.quantityTextView.text = quantity.toString()
    }

    private fun addToCart(item: ListData) {
        // Check if the item already exists in the cart
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            // Item already exists, show a toast indicating that
            Toast.makeText(this@TDetail, "Item is already in the cart", Toast.LENGTH_SHORT).show()
        } else {
            // Item doesn't exist, add it to the cart
            cartItems.add(item)
            Log.d("TDetail", "Item added to cart: ${item.name}")
            Log.d("TDetail", "Current cart items: $cartItems")
            Toast.makeText(this@TDetail, "Item added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("cartItems", cartItems)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cartItems = savedInstanceState.getParcelableArrayList("cartItems") ?: ArrayList()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent()
        intent.putParcelableArrayListExtra("cartItems", cartItems)
        setResult(RESULT_OK, intent)
        finish()
    }
}
