package com.agrospread.agrospread.agrospread.View


//changes
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.agrospread.agrospread.R

class ShareDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = requireActivity().layoutInflater.inflate(R.layout.dialog_share, null)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(dialogView)
        val dialog = builder.create()

        // Find views in the dialog
        val facebookOption = dialogView.findViewById<ImageView>(R.id.image_facebook)
        val smsOption = dialogView.findViewById<ImageView>(R.id.image_sms)
        val emailOption = dialogView.findViewById<ImageView>(R.id.image_email)

        // Set onClickListeners for each option
        facebookOption.setOnClickListener {
            openFacebook() // Call the function to open Facebook
            dialog.dismiss() // Dismiss the dialog after action
        }

        smsOption.setOnClickListener {
            openSms() // Call the function to open SMS
            dialog.dismiss() // Dismiss the dialog after action
        }

        emailOption.setOnClickListener {
            openEmail() // Call the function to open Email
            dialog.dismiss() // Dismiss the dialog after action
        }

        return dialog
    }

    private fun openFacebook() {
        val facebookUrl = "https://www.facebook.com" // Your Facebook page URL
        openLink(facebookUrl, "Facebook")
    }

    private fun openSms() {
        // You can handle SMS separately or leave it as it is
        Toast.makeText(requireContext(), "SMS option clicked", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openEmail() {
        val recipient = "recipient@example.com" // Replace with the recipient's email address
        val subject = "Check out this app!" // Replace with your desired subject
        val body = "I wanted to share this app with you." // Replace with your desired email body

        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(emailIntent)
        } else {
            Toast.makeText(requireContext(), "Gmail app not installed", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun openLink(link: String, appName: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "$appName app not installed", Toast.LENGTH_SHORT).show()
        }
    }
}
