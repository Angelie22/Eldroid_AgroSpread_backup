package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.agrospread.agrospread.R
class AccountSecurityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account_security, container, false)

        // Hide the toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()

        // Handle back button
        val backButton: Button = view.findViewById(R.id.backToSettingsBtn)
        backButton.setOnClickListener {
            navigateBackToSettings()
        }

        // Set up ListView
        val items = listOf(
            "My Profile",
            "Username",
            "Phone",
            "Email",
            "Social Media Accounts",
            "Change Password"
        )

        val listView: ListView = view.findViewById(R.id.account_security_list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        // Handle item clicks
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> navigateTo(MyProfileFragment())
                1 -> navigateTo(UsernameFragment())
                2 -> navigateTo(PhoneFragment())
                3 -> navigateTo(EmailFragment())
                4 -> navigateTo(SocialMediaFragment())
                5 -> navigateTo(ChangePasswordFragment())
            }
        }

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

    private fun navigateTo(fragment: Fragment) {
        // Replace the current fragment with the destination fragment
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
