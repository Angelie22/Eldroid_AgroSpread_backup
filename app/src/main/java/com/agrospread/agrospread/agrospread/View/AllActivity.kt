package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R
import com.agrospread.agrospread.databinding.ActivityAllBinding
import com.agrospread.agrospread.agrospread.Model.ListData
import com.agrospread.agrospread.agrospread.View.Adapters.ListAdapter
import com.agrospread.agrospread.agrospread.ViewModel.AllActivityViewModel

class AllActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllBinding
    private lateinit var listAdapter: ListAdapter
    private var dataArrayList = ArrayList<ListData>()
    private val viewModel: AllActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe cart and favorite items
        viewModel.cartItems.observe(this, Observer {

        })

        viewModel.favItems.observe(this, Observer {

        })

        // Initialize product data
        initializeProductData()

        // Initialize the ListAdapter and pass lambdas to interact with ViewModel
        listAdapter = ListAdapter(this, dataArrayList, ::addToCart, ::addToFavorites)
        binding.listView.adapter = listAdapter
    }

    private fun initializeProductData() {
        val nameList = arrayOf(
            getString(R.string.product_name_fertilizer_1),
            getString(R.string.product_name_fertilizer_2),
            getString(R.string.product_name_fertilizer_3),
            getString(R.string.product_name_fertilizer_4),
            getString(R.string.product_name_pesticide_1),
            getString(R.string.product_name_pesticide_2),
            getString(R.string.product_name_pesticide_3),
            getString(R.string.product_name_pesticide_4),
            getString(R.string.product_name_seed_1),
            getString(R.string.product_name_seed_2),
            getString(R.string.product_name_seed_3),
            getString(R.string.product_name_seed_4),
            getString(R.string.product_name_tool_1),
            getString(R.string.product_name_tool_2),
            getString(R.string.product_name_tool_3),
            getString(R.string.product_name_tool_4)
        )

        val imageList = intArrayOf(
            R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4,
            R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4,
            R.drawable.t1, R.drawable.t2, R.drawable.t3, R.drawable.t4
        )

        val priceList = arrayOf(
            getString(R.string.price_fertilizer_1),
            getString(R.string.price_fertilizer_2),
            getString(R.string.price_fertilizer_3),
            getString(R.string.price_fertilizer_4),
            getString(R.string.price_pesticide_1),
            getString(R.string.price_pesticide_2),
            getString(R.string.price_pesticide_3),
            getString(R.string.price_pesticide_4),
            getString(R.string.price_seed_1),
            getString(R.string.price_seed_2),
            getString(R.string.price_seed_3),
            getString(R.string.price_seed_4),
            getString(R.string.price_tool_1),
            getString(R.string.price_tool_2),
            getString(R.string.price_tool_3),
            getString(R.string.price_tool_4)
        )

        for (i in imageList.indices) {
            val listData = ListData(
                nameList[i],
                imageList[i],
                priceList[i]
            )
            dataArrayList.add(listData)
        }
    }

    private fun addToCart(item: ListData) {
        viewModel.addToCart(item)
        Toast.makeText(this, getString(R.string.item_added_to_cart), Toast.LENGTH_SHORT).show()
    }

    private fun addToFavorites(item: ListData) {
        viewModel.addToFavorites(item)
        Toast.makeText(this, getString(R.string.item_added_to_favorites), Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putParcelableArrayListExtra("cartItems", viewModel.cartItems.value)
        intent.putParcelableArrayListExtra("favItems", viewModel.favItems.value)
        setResult(RESULT_OK, intent)
        finish()
    }
}
