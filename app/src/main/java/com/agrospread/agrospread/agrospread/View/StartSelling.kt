package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R


class StartSelling : AppCompatActivity() {

    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_selling)

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            navigateBackToProfileFragment()
        }

        val reactivateButton: Button = findViewById(R.id.reactivateSelling)
        reactivateButton.setOnClickListener {
            showReactivateDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        // Hide the toolbar
        supportActionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        // Show the toolbar if it was previously hidden
        supportActionBar?.show()
    }

    private fun showReactivateDialog() {
        val dialogView = layoutInflater.inflate(R.layout.selling_dialog_message, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val okButton: Button = dialogView.findViewById(R.id.okButton)
        okButton.setOnClickListener {
            dialog.dismiss()
            navigateToAddProductActivity()
        }

        dialog.show()
    }

    private fun navigateToAddProductActivity() {
        val intent = Intent(this, AddProductActivity::class.java)
        startActivity(intent)
    }

    private fun navigateBackToProfileFragment() {
        val fragment = ProfileFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
