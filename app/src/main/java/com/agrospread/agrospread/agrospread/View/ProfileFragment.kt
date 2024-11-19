package com.agrospread.agrospread.agrospread.View

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.agrospread.agrospread.R
import com.agrospread.agrospread.agrospread.ViewModel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val payButton: Button = view.findViewById(R.id.payButton)
        val sellingButton: Button = view.findViewById(R.id.sellingButton)
        val buttonSettings: Button = view.findViewById(R.id.buttonSettings)
        val addressButton: Button = view.findViewById(R.id.addressButton)
        val editProfileButton: Button = view.findViewById(R.id.editPf)

        // Set click listeners to call ViewModel functions
        payButton.setOnClickListener { viewModel.onPayButtonClicked() }
        sellingButton.setOnClickListener { viewModel.onSellingButtonClicked() }
        buttonSettings.setOnClickListener { viewModel.onSettingsButtonClicked() }
        addressButton.setOnClickListener { viewModel.onAddressButtonClicked() }
        editProfileButton.setOnClickListener { viewModel.onEditProfileButtonClicked() }

        // Observe LiveData from ViewModel
        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewModel.navigateTo.observe(viewLifecycleOwner) { targetActivity ->
            targetActivity?.let {
                startActivity(Intent(requireContext(), it))
            }
        }

        viewModel.replaceFragment.observe(viewLifecycleOwner) { fragment ->
            fragment?.let {
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.fragment_container, it)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }
}