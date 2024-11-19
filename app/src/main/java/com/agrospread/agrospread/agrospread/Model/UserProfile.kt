package com.agrospread.agrospread.agrospread.Model

data class UserProfile(
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val address: String = "",
    val phoneNo: String = "",
    val profileImageUrl: String? = null
)
