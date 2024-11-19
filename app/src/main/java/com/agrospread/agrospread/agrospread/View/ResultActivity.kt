package com.agrospread.agrospread.agrospread.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.ListData
import com.agrospread.agrospread.agrospread.View.Adapters.ListAdapter

class ResultActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter
    private var cartItems = ArrayList<ListData>()
    private var favItems = ArrayList<ListData>()

    private val items = listOf(
        ListData("Miracle Go All Purpose Plant Food", R.drawable.f1, "Price: 3647"),
        ListData("Safer BIONEEM", R.drawable.p1, "Price: 755"),
        ListData("GIANTS Sunflower Seed ORIGINAL", R.drawable.s1, "Price: 345"),
        ListData("Shovel for precision farming", R.drawable.t1, "Price: 199"),
        ListData("Plantmate Organic Fertilizer", R.drawable.f2, "Price: 980"),
        ListData("Safer Lawn RESTORE", R.drawable.f3, "Price: 550"),
        ListData("Expert Gardener Organics", R.drawable.f4, "Price: 567"),
        ListData("Sprinkler for smart irrigation", R.drawable.sprinkler, "Price: 2566"),
        ListData("4 Season Vegetable seeds", R.drawable.seed, "Price: 567"),
        ListData("Triazicide insect killer", R.drawable.p2, "Price: 568"),
        ListData("Garden safe Fungicide 3", R.drawable.p3, "Price: 598"),
        ListData("Blue Magic Pesticide", R.drawable.p4, "Price: 689"),
        ListData("Orgabic Chia Seed", R.drawable.s2, "Price: 859"),
        ListData("Nutiva Chia Seed", R.drawable.s3, "Price: 565"),
        ListData("Roasted and Salted Sunflower Seed", R.drawable.s4, "Price: 546"),
        ListData("Fruits from Seeds", R.drawable.a, "Price: 18"),
        ListData("Non-flower vegetables Fertilizer", R.drawable.n, "Price: 18"),
        ListData("Inorganic Fertilizer", R.drawable.`in`, "Price: 18"),
        ListData("Alaska Fish Emulsion Fertilizer", R.drawable.f, "Price: 18"),
        ListData("Arbico Organics Earthworm Castings", R.drawable.e, "Price: 18"),
        ListData("Burpee Bone Meal", R.drawable.b, "Price: 18"),
        ListData("Dr. Earth Bulb Food", R.drawable.d, "Price: 18")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Restore saved state
        savedInstanceState?.let {
            cartItems = it.getParcelableArrayList("cartItems") ?: ArrayList()
            favItems = it.getParcelableArrayList("favItems") ?: ArrayList()
        }

        // Retrieve passed data from MainActivity
        intent.extras?.let {
            cartItems = it.getParcelableArrayList("cartItems") ?: ArrayList()
            favItems = it.getParcelableArrayList("favItems") ?: ArrayList()
        }

        val listView = findViewById<ListView>(R.id.listView)
        val query = intent.getStringExtra("query") ?: ""
        val filteredListData = items.filter { it.name.contains(query, ignoreCase = true) }
        listAdapter = ListAdapter(this, ArrayList(filteredListData), this::addToCart, this::addToFavorites)
        listView.adapter = listAdapter
    }

    private fun addToCart(item: ListData) {
        if (!cartItems.contains(item)) {
            cartItems.add(item)
            Log.d("ResultActivity", "Item added to cart: ${item.name}")
            Log.d("ResultActivity", "Current cart items: $cartItems")
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("ResultActivity", "Item is already in the cart: ${item.name}")
            Toast.makeText(this, "Item is already in the cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToFavorites(item: ListData) {
        if (!favItems.contains(item)) {
            favItems.add(item)
            Log.d("ResultActivity", "Item added to favorites: ${item.name}")
            Log.d("ResultActivity", "Current favorite items: $favItems")
            Toast.makeText(this, "Item added to favorites", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("ResultActivity", "Item is already in the favorites: ${item.name}")
            Toast.makeText(this, "Item is already in the favorites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("cartItems", cartItems)
        outState.putParcelableArrayList("favItems", favItems)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cartItems = savedInstanceState.getParcelableArrayList("cartItems") ?: ArrayList()
        favItems = savedInstanceState.getParcelableArrayList("favItems") ?: ArrayList()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent()
        intent.putParcelableArrayListExtra("cartItems", cartItems)
        intent.putParcelableArrayListExtra("favItems", favItems)
        setResult(RESULT_OK, intent)
        finish()
    }
}