package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agrospread.agrospread.R


class LogoutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }

    // Add method to handle logout action
    private fun logout() {

        //Intent to navigate to the login activity
        val intent = Intent(activity, Login::class.java)
        // Add flags to clear back stack and create a new task
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        // Start the login activity
        startActivity(intent)
        // Optional: Finish the current activity
        requireActivity().finish()
    }
}