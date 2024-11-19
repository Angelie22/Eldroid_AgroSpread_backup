package com.agrospread.agrospread.agrospread.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.View.DashboardFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the "View Products" button
        val viewProductsButton: Button = view.findViewById(R.id.viewProductsButton)

        // Set OnClickListener to the button
        viewProductsButton.setOnClickListener {
            // Replace the current fragment with the DashboardFragment
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment())
                .addToBackStack(null) // Optional: Add the transaction to the back stack
                .commit()
        }

        return view
    }
}
