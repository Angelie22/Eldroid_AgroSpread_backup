package com.agrospread.agrospread.agrospread.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.ListData
import com.agrospread.agrospread.agrospread.View.Adapters.CartAdapter
import com.agrospread.agrospread.databinding.ActivityCartBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Cart : AppCompatActivity(), CartAdapter.CartItemClickListener {

    private lateinit var binding: ActivityCartBinding
    private var cartItems: ArrayList<ListData> = ArrayList()
    private lateinit var cartAdapter: CartAdapter
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartItems = intent.getParcelableArrayListExtra("cartItems") ?: ArrayList()

        cartAdapter = CartAdapter(this, cartItems, this)
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.adapter = cartAdapter

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val removedItem = cartItems[position]
                removeItemFromCart(position)

                Snackbar.make(binding.recyclerViewCart, "${removedItem.name} removed from cart", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        addItemToCart(position, removedItem)
                    }.show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewCart)

        updateTotalPrice()

        binding.Checkout.setOnClickListener {
            if (cartItems.isEmpty()) {
                Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
            } else {
                val totalPrice = binding.totalTextView.text.toString().substringAfter("$").toInt()
                showCheckoutDialog(totalPrice)
            }
        }
    }

    override fun onQuantityChanged() {
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        var total = 0
        for (item in cartItems) {
            total += item.price.replace("Price: ", "").toInt() * item.quantity
        }
        binding.totalTextView.text = "Total: $$total"
    }

    private fun removeItemFromCart(position: Int) {
        cartAdapter.removeItemAt(position)
        updateTotalPrice()
    }

    private fun addItemToCart(position: Int, item: ListData) {
        cartAdapter.addItemAt(position, item)
        updateTotalPrice()
    }

    @SuppressLint("SetTextI18n")
    private fun showCheckoutDialog(totalPrice: Int) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.checkout, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Checkout")

        val alertDialog = builder.show()

        val editTextName: EditText = dialogView.findViewById(R.id.editTextName)
        val editTextAddress: EditText = dialogView.findViewById(R.id.editTextAddress)
        val editTextPaymentMethod: EditText = dialogView.findViewById(R.id.editTextPaymentMethod)
        val editTextPhoneNumber: EditText = dialogView.findViewById(R.id.editTextPhoneNumber)
        val textViewTotalPrice: TextView = dialogView.findViewById(R.id.textViewTotalPrice)
        val buttonPayNow: Button = dialogView.findViewById(R.id.buttonPayNow)

        // Set total price dynamically
        textViewTotalPrice.text = "Total Price: $$totalPrice"

        // Fetch user data from Firestore
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId).get().addOnSuccessListener { document ->
                if (document != null) {
                    editTextName.setText(document.getString("fullName"))
                    editTextAddress.setText(document.getString("address"))
                    editTextPhoneNumber.setText(document.getString("phoneNo"))
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to fetch user data", Toast.LENGTH_SHORT).show()
            }
        }

        buttonPayNow.setOnClickListener {
            val name = editTextName.text.toString()
            val address = editTextAddress.text.toString()
            val paymentMethod = editTextPaymentMethod.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            if (name.isNotEmpty() && address.isNotEmpty() && paymentMethod.isNotEmpty() && phoneNumber.isNotEmpty()) {
                // Save the new order details in SharedPreferences
                val sharedPreferences = getSharedPreferences("CheckoutInfo", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("address", address)
                editor.putString("paymentMethod", paymentMethod)
                editor.putString("phoneNumber", phoneNumber)
                editor.putInt("totalPrice", totalPrice)
                editor.apply()

                // Save checkout information to Firestore
                val checkoutInfo = hashMapOf(
                    "name" to name,
                    "address" to address,
                    "paymentMethod" to paymentMethod,
                    "phoneNumber" to phoneNumber,
                    "totalPrice" to totalPrice,
                    "cartItems" to cartItems.map { item ->
                        hashMapOf(
                            "name" to item.name,
                            "price" to item.price,
                            "quantity" to item.quantity
                        )
                    }
                )
                if (userId != null) {
                    db.collection("checkoutInfo").document(userId).set(checkoutInfo)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Checkout Success", Toast.LENGTH_SHORT).show()
                            clearCart()
                            alertDialog.dismiss()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error saving checkout info: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        alertDialog.setOnCancelListener {
            Toast.makeText(this, "Checkout Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearCart() {
        cartItems.clear()
        cartAdapter.notifyDataSetChanged()
        updateTotalPrice()
    }
}

