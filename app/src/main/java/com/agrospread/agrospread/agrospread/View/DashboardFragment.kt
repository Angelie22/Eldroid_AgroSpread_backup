package com.agrospread.agrospread.agrospread.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.ListData
import com.agrospread.agrospread.agrospread.Model.OfferData
import com.agrospread.agrospread.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var cartItems = ArrayList<ListData>()
    private var cartsItems = ArrayList<OfferData>()
    private var favItems = ArrayList<ListData>()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore saved state
        savedInstanceState?.let {
            cartItems = it.getParcelableArrayList("cartItems") ?: ArrayList()
            favItems = it.getParcelableArrayList("favItems") ?: ArrayList()
        }

        // Initialize buttons and set click listeners
        binding.btnFertilizer.setOnClickListener {
            val intent = Intent(requireActivity(), FertilizersActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivityForResult(intent, REQUEST_CODE_FERTILIZERS)
        }

        binding.btnPesticide.setOnClickListener {
            val intent = Intent(requireActivity(), PesticideActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivityForResult(intent, REQUEST_CODE_PESTICIDES)
        }

        binding.btnSeeds.setOnClickListener {
            val intent = Intent(requireActivity(), SeedActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivityForResult(intent, REQUEST_CODE_SEEDS)
        }

        binding.btnTools.setOnClickListener {
            val intent = Intent(requireActivity(), ToolsActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivityForResult(intent, REQUEST_CODE_TOOLS)
        }

        binding.btnAll.setOnClickListener {
            val intent = Intent(requireActivity(), AllActivity::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivityForResult(intent, REQUEST_CODE_ALL)
        }

        binding.featured1.setOnClickListener {
            val intent = Intent(requireActivity(), FDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured1)
        }

        binding.featured2.setOnClickListener {
            val intent = Intent(requireActivity(), PDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured2)
        }

        binding.featured3.setOnClickListener {
            val intent = Intent(requireActivity(), SDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured3)
        }

        binding.featured4.setOnClickListener {
            val intent = Intent(requireActivity(), TDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured4)
        }

        binding.featured5.setOnClickListener {
            val intent = Intent(requireActivity(), SeedDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured5)
        }

        binding.featured6.setOnClickListener {
            val intent = Intent(requireActivity(), SprinklerDetail::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivityForResult(intent, REQUEST_CODE_featured6)
        }



        binding.showBasket.setOnClickListener {
            val intent = Intent(requireActivity(), Cart::class.java)
            intent.putParcelableArrayListExtra("cartItems", cartItems)
            startActivity(intent)
        }

        binding.showFavorite.setOnClickListener {
            val intent = Intent(requireActivity(), Favorites::class.java)
            intent.putParcelableArrayListExtra("favItems", favItems)
            startActivity(intent)
        }

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val intent = Intent(requireContext(), ResultActivity::class.java)
                    intent.putExtra("query", it)
                    intent.putParcelableArrayListExtra("cartItems", cartItems)
                    intent.putParcelableArrayListExtra("favItems", favItems)
                    startActivityForResult(intent, REQUEST_CODE_searchView)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle text change if needed
                return false
            }
        })

        // Setting up bestseller item 1
        setupBestSellerItems()

        // Adding checkbox functionality
        setupWishlistItems()

    }

    //this is for add to cart best seller
    private fun setupBestSellerItems() {
        binding.addToCart1.setOnClickListener {
            val bestsellerItem1 = ListData(
                name = "All-purpose",
                image = R.drawable.fertilizer,
                price = "Price: 199",
                quantity = 1
            )

            addItemToCart(bestsellerItem1)
        }
        binding.addToCart2.setOnClickListener {
            val bestsellerItem2 = ListData(
                name = "Triazicide",
                image = R.drawable.pest,
                price = "Price: 200",
                quantity = 1
            )
            addItemToCart(bestsellerItem2)
        }
        binding.addToCart3.setOnClickListener {
            val bestsellerItem3 = ListData(
                name = "Chia Seeds",
                image = R.drawable.seeds,
                price = "Price: 100",
                quantity = 1
            )
            addItemToCart(bestsellerItem3)
        }
        binding.addToCart4.setOnClickListener {
            val bestsellerItem4 = ListData(
                name = "Shovels",
                image = R.drawable.tools,
                price = "Price: 150",
                quantity = 1
            )
            addItemToCart(bestsellerItem4)
        }
    }
    // Setting up wishlist items
    private fun setupWishlistItems() {
        // Set onClickListener for the wishlist button
        binding.wishlist.setOnClickListener {
            // Add item to favorites when wishlist button is clicked
            val bestsellerItem = ListData(
                name = "All-purpose",
                image = R.drawable.fertilizer,
                price = "Price: 199",
                quantity = 1
            )
            addItemToFavorites(bestsellerItem)
        }
        binding.wishlist1.setOnClickListener {
            // Add item to favorites when wishlist button is clicked
            val bestsellerItem1 = ListData(
                name = "Triazicide",
                image = R.drawable.pest,
                price = "Price: 200",
                quantity = 1
            )
            addItemToFavorites(bestsellerItem1)
        }
        binding.wishlist2.setOnClickListener {
            // Add item to favorites when wishlist button is clicked
            val bestsellerItem2 = ListData(
                name = "Chia Seeds",
                image = R.drawable.seeds,
                price = "Price: 100",
                quantity = 1
            )
            addItemToFavorites(bestsellerItem2)
        }
        binding.wishlist3.setOnClickListener {
            // Add item to favorites when wishlist button is clicked
            val bestsellerItem3 = ListData(
                name = "Shovels",
                image = R.drawable.tools,
                price = "Price: 150",
                quantity = 1
            )
            addItemToFavorites(bestsellerItem3)
        }
    }

    private fun addItemToCart(item: ListData) {
        // Check if the item already exists in the cart
        val existingItem = cartItems.find { it.name == item.name }
        if (existingItem != null) {
            // Item already exists, show a toast indicating that
            showToast("Item already added to cart")
        } else {
            // Item doesn't exist, add it to the cart
            cartItems.add(item)
            showToast("Item added to cart")
        }
    }

    private fun addItemToFavorites(item: ListData) {
        // Check if the item already exists in the cart
        val existingItem = favItems.find { it.name == item.name }
        if (existingItem != null) {
            // Item already exists, show a toast indicating that
            showToast("Item already added to favorite")
        } else {
            // Item doesn't exist, add it to the cart
            favItems.add(item)
            showToast("Item added to favorite")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_FERTILIZERS, REQUEST_CODE_PESTICIDES, REQUEST_CODE_SEEDS , REQUEST_CODE_TOOLS, REQUEST_CODE_ALL, REQUEST_CODE_featured1,
                REQUEST_CODE_featured2, REQUEST_CODE_featured3, REQUEST_CODE_featured4, REQUEST_CODE_featured5, REQUEST_CODE_featured6, REQUEST_CODE_searchView -> {
                    cartItems = data?.getParcelableArrayListExtra("cartItems") ?: ArrayList()
                    favItems = data?.getParcelableArrayListExtra("favItems") ?: ArrayList()
                    val selectedItem = data?.getParcelableExtra<ListData>("selectedItem")
                    selectedItem?.let { addItemToCart(it) }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("cartItems", cartItems)
        outState.putParcelableArrayList("favItems", favItems)
    }

    private fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val REQUEST_CODE_FERTILIZERS = 1
        private const val REQUEST_CODE_PESTICIDES = 2
        private const val REQUEST_CODE_SEEDS = 3
        private const val REQUEST_CODE_TOOLS = 4
        private const val REQUEST_CODE_ALL = 5
        private const val REQUEST_CODE_featured1 = 6
        private const val REQUEST_CODE_featured2 = 7
        private const val REQUEST_CODE_featured3 = 8
        private const val REQUEST_CODE_featured4 = 9
        private const val REQUEST_CODE_featured5 = 10
        private const val REQUEST_CODE_featured6 = 11
        private const val REQUEST_CODE_searchView =12
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}