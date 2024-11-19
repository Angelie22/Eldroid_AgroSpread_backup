package com.agrospread.agrospread.agrospread.Model

data class User(
    val username: String,
    val fullName: String,
    val email: String,
    val password: String, // Consider encrypting this before saving
    val phoneNo: String,
    val address: String
)
