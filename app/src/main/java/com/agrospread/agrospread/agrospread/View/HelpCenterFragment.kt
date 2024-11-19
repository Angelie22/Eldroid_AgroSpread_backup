package com.agrospread.agrospread.agrospread.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.agrospread.agrospread.R

class HelpCenterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_help_center, container, false)

        // Hide the toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()

        // Handle back button
        val backButton: Button = view.findViewById(R.id.backToSettingsHelpBtn)
        backButton.setOnClickListener {
            navigateBackToSettings()
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
}
