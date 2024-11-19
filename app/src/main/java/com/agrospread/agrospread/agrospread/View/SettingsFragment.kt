package com.agrospread.agrospread.agrospread.View


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.app.AppCompatDelegate
import android.widget.Toast
import com.agrospread.agrospread.R

class SettingsFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize sharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("NIGHT_MODE", false)

        // Retrieve user data from SharedPreferences
        val userPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val fullName = userPreferences.getString("fullName", "")
        val phoneNo = userPreferences.getString("phoneNo", "+")

        // Set user data in TextViews
        val fullNameTextView = view.findViewById<TextView>(R.id.textViewFullName)
        val phoneNoTextView = view.findViewById<TextView>(R.id.textViewPhoneNo)

        fullNameTextView.text = fullName
        phoneNoTextView.text = phoneNo

        // Set background based on the current mode
        val layout = view.findViewById<View>(R.id.settingsLayout)
        setBackground(layout, isNightMode)

        // TextView enable click behavior
        val accountSecurityTextView = view.findViewById<TextView>(R.id.accountSecurityTextView)
        val myAddressTextView = view.findViewById<TextView>(R.id.myAddressTextView)
        val bankAccountsTextView = view.findViewById<TextView>(R.id.bankAccountsTextView)
        val helpCenterTextView = view.findViewById<TextView>(R.id.helpCenterTextView)
        val appPoliciesTextView = view.findViewById<TextView>(R.id.appPoliciesTextView)

        // OnClickListener to handle the click event for each TextView
        accountSecurityTextView.setOnClickListener {
            // Navigate to Account & Security Fragment
            navigateTo(AccountSecurityFragment())
        }

        myAddressTextView.setOnClickListener {
            // Navigate to My Addresses Fragment
            navigateTo(MyAddressFragment())
        }

        bankAccountsTextView.setOnClickListener {
            navigateTo(BankAccountsFragment())
        }

        helpCenterTextView.setOnClickListener {
            navigateTo(HelpCenterFragment())
        }

        appPoliciesTextView.setOnClickListener {
            navigateTo(AppPoliciesFragment())
        }

        // Switch for Night Mode
        val switchNightMode = view.findViewById<SwitchCompat>(R.id.switchNightMode)
        switchNightMode.isChecked = isNightMode
        switchNightMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                setBackground(layout, true)
                sharedPreferences.edit().putBoolean("NIGHT_MODE", true).apply()
                Toast.makeText(context, "Night Mode Enabled", Toast.LENGTH_SHORT).show()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                setBackground(layout, false)
                sharedPreferences.edit().putBoolean("NIGHT_MODE", false).apply()
                Toast.makeText(context, "Night Mode Disabled", Toast.LENGTH_SHORT).show()
            }
        }

        // Switch for Notifications
        val switchNotifications = view.findViewById<SwitchCompat>(R.id.switchNotifications)
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(context, "Notifications Enabled", Toast.LENGTH_SHORT).show()
                // Enable notifications logic here
            } else {
                Toast.makeText(context, "Notifications Disabled", Toast.LENGTH_SHORT).show()
                // Disable notifications logic here
            }
        }

        return view
    }

    private fun setBackground(layout: View, isNightMode: Boolean) {
        if (isNightMode) {
            layout.setBackgroundResource(R.drawable.bg_dark)
        } else {
            layout.setBackgroundResource(R.drawable.bg) // Use your default background image here
        }
    }

    private fun navigateTo(fragment: Fragment) {
        // Replace the current fragment with the destination fragment
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null) // Optional: Add transaction to back stack
        transaction.commit()
    }
}
