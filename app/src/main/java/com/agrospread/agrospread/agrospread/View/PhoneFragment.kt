package com.agrospread.agrospread.agrospread.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.agrospread.agrospread.R

class PhoneFragment : Fragment() {
    private lateinit var backButton: Button // Assuming you have a back button defined
    private var toolbarVisible = true // Keep track of toolbar visibility

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_phone, container, false)

        backButton = view.findViewById(R.id.backToAccountSecurityBtn) // Replace with your button's id
        backButton.setOnClickListener {
            navigateBackToAccountSecurity()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        // Hide the toolbar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        toolbarVisible = false // Update visibility tracker
    }

    override fun onPause() {
        super.onPause()
        // Optionally show the toolbar if previously hidden
        if (isToolbarVisible()) {
            (requireActivity() as AppCompatActivity).supportActionBar?.show()
        }
    }

    private fun isToolbarVisible(): Boolean {
        // Add logic to determine toolbar visibility based on your needs
        return toolbarVisible
    }

    private fun navigateBackToAccountSecurity() {
        // Navigate back to AccountSecurityFragment
        val fragment = AccountSecurityFragment() // Create an instance of the fragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}
