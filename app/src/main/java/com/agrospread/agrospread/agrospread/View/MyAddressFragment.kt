package com.agrospread.agrospread.agrospread.View


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.agrospread.agrospread.R

class MyAddressFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_address, container, false)

        // Hide the toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()

        // Handle back button
        val backButton: Button = view.findViewById(R.id.backToSettingsAddressBtn)
        backButton.setOnClickListener {
            navigateBackToSettings()
        }

        // Retrieve user data from SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val fullName = sharedPreferences.getString("fullName", "")
        val phoneNo = sharedPreferences.getString("phoneNo", "")
        val address = sharedPreferences.getString("address", "")

        // Set user data in TextViews
        val fullNameTextView = view.findViewById<TextView>(R.id.textViewFullName)
        val phoneNoTextView = view.findViewById<TextView>(R.id.textViewPhoneNo)
        val addressTextView = view.findViewById<TextView>(R.id.textViewAddress)

        fullNameTextView.text = fullName
        phoneNoTextView.text = phoneNo
        addressTextView.text = address

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Show the toolbar when the fragment is destroyed
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.show()
    }

    private fun navigateBackToSettings() {
        // Replace the current fragment with the SettingsFragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .commit()
    }
}
