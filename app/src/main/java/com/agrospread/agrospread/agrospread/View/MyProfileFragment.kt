package com.agrospread.agrospread.agrospread.View

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.ViewModel.MyProfileViewModel
import com.agrospread.agrospread.databinding.FragmentMyProfileBinding
import com.squareup.picasso.Picasso

class MyProfileFragment : Fragment() {
    private val viewModel: MyProfileViewModel by viewModels()
    private var fileUri: Uri? = null
    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()

        viewModel.fetchUserProfile()
    }

    private fun setupObservers() {
        viewModel.userProfile.observe(viewLifecycleOwner, Observer { profile ->
            if (profile != null) {
                binding.profileName.text = profile.fullName
            }
            if (profile != null) {
                binding.profileUsername.text = profile.username
            }
            if (profile != null) {
                binding.profileEmail.text = profile.email
            }
            if (profile != null) {
                binding.profilePassword.text = profile.password
            }
            if (profile != null) {
                binding.profileAddress.text = profile.address
            }
            if (profile != null) {
                binding.profilePhone.text = profile.phoneNo
            }

            if (profile != null) {
                if (!profile.profileImageUrl.isNullOrEmpty()) {
                    if (profile != null) {
                        Picasso.get().load(profile.profileImageUrl).into(binding.imageView)
                    }
                }
            }
        })

        viewModel.uploadStatus.observe(viewLifecycleOwner, Observer { status ->
            if (!status.isNullOrEmpty()) {
                Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupListeners() {
        // Using View Binding for accessing views by ID
        binding.chooseImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, 0)
        }

        binding.uploadImage.setOnClickListener {
            viewModel.uploadImage(fileUri)
        }

        // Long click listeners to show edit dialogs for fields
        binding.profileName.setOnLongClickListener { showEditDialog("fullName", binding.profileName.text.toString()); true }
        binding.profileUsername.setOnLongClickListener { showEditDialog("username", binding.profileUsername.text.toString()); true }
        binding.profileEmail.setOnLongClickListener { showEditDialog("email", binding.profileEmail.text.toString()); true }
        binding.profilePassword.setOnLongClickListener { showEditDialog("password", binding.profilePassword.text.toString()); true }
        binding.profileAddress.setOnLongClickListener { showEditDialog("address", binding.profileAddress.text.toString()); true }
        binding.profilePhone.setOnLongClickListener { showEditDialog("phoneNo", binding.profilePhone.text.toString()); true }
    }

    private fun showEditDialog(field: String, currentValue: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.edit_profile))

        val input = EditText(requireContext()).apply {
            setText(currentValue)
        }
        builder.setView(input)

        builder.setPositiveButton(getString(R.string.save_button)) { dialog, _ ->
            viewModel.updateProfileField(field, input.text.toString())
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel_button)) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK) {
            fileUri = data?.data
            Picasso.get().load(fileUri).into(binding.imageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}