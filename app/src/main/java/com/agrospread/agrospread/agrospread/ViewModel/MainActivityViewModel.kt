package com.agrospread.agrospread.agrospread.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> get() = _userData

    fun loadUserData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val fullName = sharedPreferences.getString("fullName", "")
        val email = sharedPreferences.getString("email", "")

        _userData.postValue(UserData(username, fullName, email))
    }
}

data class UserData(
    val username: String?,
    val fullName: String?,
    val email: String?
)
