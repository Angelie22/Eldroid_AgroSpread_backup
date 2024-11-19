package com.agrospread.agrospread.agrospread.View



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import com.agrospread.agrospread.R

class ShareFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showShareDialog()
    }

    private fun showShareDialog() {
        // Inflate the custom dialog layout
        val dialogView = layoutInflater.inflate(R.layout.dialog_share, null)

        // Initialize dialog builder
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)
        val dialog = builder.create()

        // Find views in the dialog
        val facebookOption = dialogView.findViewById<View>(R.id.facebook)
        val smsOption = dialogView.findViewById<View>(R.id.sms)
        val emailOption = dialogView.findViewById<View>(R.id.email)

        // Set onClickListeners for each option
        facebookOption.setOnClickListener {
            // Handle Facebook share action
            // Example: shareToFacebook()
            dialog.dismiss() // Dismiss the dialog after action
        }

        smsOption.setOnClickListener {
            // Handle SMS share action
            // Example: shareViaSMS()
            dialog.dismiss() // Dismiss the dialog after action
        }

        emailOption.setOnClickListener {
            // Handle Email share action
            // Example: shareViaEmail()
            dialog.dismiss() // Dismiss the dialog after action
        }

        // Show the dialog
        dialog.show()
    }

    // Example functions for handling share actions
    private fun shareToFacebook() {
        // Implement Facebook sharing logic
    }

    private fun shareViaSMS() {
        // Implement SMS sharing logic
    }

    private fun shareViaEmail() {
        // Implement Email sharing logic
    }
}
