package com.agrospread.agrospread.agrospread.View


import com.agrospread.agrospread.agrospread.Model.ListData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.View.Adapters.ListAdapter
import com.agrospread.agrospread.agrospread.ViewModel.PesticideViewModel
import com.agrospread.agrospread.databinding.ActivityPesticideBinding

class PesticideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesticideBinding
    private lateinit var listAdapter: ListAdapter
    private val viewModel: PesticideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesticideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restore saved state
        savedInstanceState?.let {
            viewModel.addToCart(it.getParcelableArrayList<ListData>("cartItems") ?: ArrayList())
            viewModel.addToFavorites(it.getParcelableArrayList<ListData>("favItems") ?: ArrayList())
        }

        // Retrieve passed data from MainActivity
        intent.extras?.let {
            viewModel.addToCart(it.getParcelableArrayList<ListData>("cartItems") ?: ArrayList())
            viewModel.addToFavorites(it.getParcelableArrayList<ListData>("favItems") ?: ArrayList())
        }

        setupData()
        setupListAdapter()
    }

    private fun setupData() {
        val nameList = arrayOf(
            getString(R.string.pesticide_1_name),
            getString(R.string.pesticide_2_name),
            getString(R.string.pesticide_3_name),
            getString(R.string.pesticide_4_name)
        )

        val imageList = intArrayOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4
        )

        val priceList = arrayOf(
            getString(R.string.pesticide_1_price),
            getString(R.string.pesticide_2_price),
            getString(R.string.pesticide_3_price),
            getString(R.string.pesticide_4_price)
        )

        val dataArrayList = ArrayList<ListData>()
        for (i in imageList.indices) {
            val listData = ListData(
                nameList[i],
                imageList[i],
                priceList[i]
            )
            dataArrayList.add(listData)
        }

        viewModel.setData(dataArrayList)
    }

    private fun setupListAdapter() {
        listAdapter = ListAdapter(this, viewModel.getData(), ::addToCart, ::addToFavorites)
        binding.listView.adapter = listAdapter
    }

    private fun addToCart(item: ListData) {
        if (viewModel.addItemToCart(item)) {
            Log.d("PesticideActivity", "Item added to cart: ${item.name}")
            Toast.makeText(this, getString(R.string.toast_item_added_to_cart), Toast.LENGTH_SHORT).show()
        } else {
            Log.d("PesticideActivity", "Item is already in the cart: ${item.name}")
            Toast.makeText(this, getString(R.string.toast_item_already_in_cart), Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToFavorites(item: ListData) {
        if (viewModel.addItemToFavorites(item)) {
            Log.d("PesticideActivity", "Item added to favorites: ${item.name}")
            Toast.makeText(this, getString(R.string.toast_item_added_to_favorites), Toast.LENGTH_SHORT).show()
        } else {
            Log.d("PesticideActivity", "Item is already in favorites: ${item.name}")
            Toast.makeText(this, getString(R.string.toast_item_already_in_favorites), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("cartItems", ArrayList(viewModel.getCartItems()))
        outState.putParcelableArrayList("favItems", ArrayList(viewModel.getFavoriteItems()))
    }

    @Suppress("MissingSuperCall")
    override fun onBackPressed() {
        val intent = Intent().apply {
            putParcelableArrayListExtra("cartItems", ArrayList(viewModel.getCartItems()))
            putParcelableArrayListExtra("favItems", ArrayList(viewModel.getFavoriteItems()))
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}



