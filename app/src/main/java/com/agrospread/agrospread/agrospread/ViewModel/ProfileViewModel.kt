package com.agrospread.agrospread.agrospread.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.View.MyProfileFragment
import com.agrospread.agrospread.agrospread.View.OderDetails
import com.agrospread.agrospread.agrospread.View.StartSelling
import com.agrospread.agrospread.agrospread.View.AccountSecurityFragment
import com.agrospread.agrospread.agrospread.View.MyAddressFragment

class ProfileViewModel : ViewModel() {

    private val _navigateTo = MutableLiveData<Class<*>>()
    val navigateTo: LiveData<Class<*>>
        get() = _navigateTo

    private val _replaceFragment = MutableLiveData<Fragment>()
    val replaceFragment: LiveData<Fragment>
        get() = _replaceFragment

    fun onPayButtonClicked() {
        _navigateTo.value = OderDetails::class.java
    }

    fun onSellingButtonClicked() {
        _navigateTo.value = StartSelling::class.java
    }

    fun onSettingsButtonClicked() {
        _replaceFragment.value = AccountSecurityFragment()
    }

    fun onAddressButtonClicked() {
        _replaceFragment.value = MyAddressFragment()
    }

    fun onEditProfileButtonClicked() {
        _replaceFragment.value = MyProfileFragment()
    }
}
