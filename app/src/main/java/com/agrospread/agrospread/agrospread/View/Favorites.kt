package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.Model.ListData
import com.agrospread.agrospread.agrospread.View.Adapters.FavoriteAdapter
import com.agrospread.agrospread.agrospread.ViewModel.FavoritesViewModel
import com.agrospread.agrospread.databinding.ActivityFavoritesBinding
import com.google.android.material.snackbar.Snackbar

class Favorites : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var favAdapter: FavoriteAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe favItems from the ViewModel
        favoritesViewModel.favItems.observe(this, Observer { favItems ->
            favAdapter = FavoriteAdapter(this, favItems) { position ->
                removeItemFromFavorites(position)
            }
            binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewFavorites.adapter = favAdapter
        })

        // Set initial data from intent (if needed)
        val favItems = intent.getParcelableArrayListExtra<ListData>("favItems") ?: ArrayList()
        favoritesViewModel.setFavItems(favItems)

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
                val removedItem = favItems[position]
                removeItemFromFavorites(position)

                // Use string resource from strings.xml
                val message = getString(R.string.item_removed_from_favorites, removedItem.name)

                Snackbar.make(binding.recyclerViewFavorites, message, Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.undo)) {
                        addItemToFavorites(position, removedItem)
                    }.show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavorites)
    }

    private fun removeItemFromFavorites(position: Int) {
        favoritesViewModel.removeItem(position)
    }

    private fun addItemToFavorites(position: Int, item: ListData) {
        favoritesViewModel.addItem(position, item)
    }
}
