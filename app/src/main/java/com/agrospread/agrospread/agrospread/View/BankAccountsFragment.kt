package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.View.Adapters.BankAccountsAdapter

//changes
class BankAccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bank_accounts, container, false)

        // Hide the toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()

        // Handle back button
        val backButton: Button = view.findViewById(R.id.backToSettingsBankBtn)
        backButton.setOnClickListener {
            navigateBackToSettings()
        }

        // Setup the ListView
        val accountsListView: ListView = view.findViewById(R.id.bank_accounts_list)

        val accounts = listOf(
            BankAccountsAdapter.BankAccount(R.drawable.ic_gcash, "+  Link G-Cash Account"),
            BankAccountsAdapter.BankAccount(R.drawable.ic_bdo, "+  Link BDO Account"),
            BankAccountsAdapter.BankAccount(R.drawable.ic_paymaya, "+  Link Paymaya Account")
        )

        val adapter = BankAccountsAdapter(requireContext(), accounts)
        accountsListView.adapter = adapter

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
