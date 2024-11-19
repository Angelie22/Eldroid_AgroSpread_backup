package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.ViewModel.MainActivityViewModel
import com.agrospread.agrospread.agrospread.ViewModel.UserData
import com.agrospread.agrospread.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            binding.toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        // Load user data into ViewModel
        viewModel.loadUserData(this)

        // Observe user data from ViewModel
        viewModel.userData.observe(this) { userData ->
            updateUserHeader(userData)
        }

        // Set bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            handleBottomNavigation(item)
            true
        }

        // Open the initial fragment
        openFragment(HomeFragment())
    }

    private fun updateUserHeader(userData: UserData) {
        val headerView = binding.navigationDrawer.getHeaderView(0)
        val usernameTextView = headerView.findViewById<TextView>(R.id.textViewUsername)
        val fullNameTextView = headerView.findViewById<TextView>(R.id.textViewFullName)
        val emailTextView = headerView.findViewById<TextView>(R.id.textViewEmail)

        usernameTextView.text = userData.username
        fullNameTextView.text = userData.fullName
        emailTextView.text = userData.email
    }

    private fun handleBottomNavigation(item: MenuItem) {
        when (item.itemId) {
            R.id.bottom_dashboard -> openFragment(DashboardFragment())
            R.id.bottom_reco -> openFragment(Recommendation())
            R.id.bottom_offer -> openFragment(OfferFragment())
            R.id.bottom_profile -> openFragment(ProfileFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> openFragment(DashboardFragment())
            R.id.nav_feedback -> openFragment(FeedbackFragment())
            R.id.nav_about_us -> openFragment(AboutUsFragment())
            R.id.nav_settings -> openFragment(SettingsFragment())
            R.id.nav_logout -> logout()
            R.id.nav_share -> showShareDialog()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logout() {
        Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun showShareDialog() {
        val dialog = ShareDialogFragment()
        dialog.show(supportFragmentManager, "ShareDialog")
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
