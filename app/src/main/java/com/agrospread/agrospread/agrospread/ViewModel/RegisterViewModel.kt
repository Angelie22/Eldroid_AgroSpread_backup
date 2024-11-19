package com.agrospread.agrospread.agrospread.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _registrationStatus = MutableLiveData<String>()
    val registrationStatus: LiveData<String> get() = _registrationStatus

    fun registerUser(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser!!.uid
                    db.collection("userInfo").document(userId).set(user)
                        .addOnSuccessListener {
                            _registrationStatus.value = "Success"
                        }
                        .addOnFailureListener { e ->
                            _registrationStatus.value = "Error saving user data: ${e.message}"
                        }
                } else {
                    _registrationStatus.value = "Registration failed: ${task.exception?.message}"
                }
            }
    }
}
