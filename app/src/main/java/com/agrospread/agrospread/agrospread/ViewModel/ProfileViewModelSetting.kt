package com.agrospread.agrospread.agrospread.ViewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.agrospread.agrospread.agrospread.Model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class ProfileViewModelSetting : ViewModel() {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    private val _userProfile = MutableLiveData<UserProfile>()
    val userProfile: LiveData<UserProfile> = _userProfile

    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> = _statusMessage

    fun fetchUserProfile() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        _userProfile.value = document.toObject(UserProfile::class.java)
                    }
                }
                .addOnFailureListener { e ->
                    _statusMessage.value = "Error fetching user profile: ${e.message}"
                }
        }
    }

    fun updateUserProfileField(field: String, value: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId)
                .update(field, value)
                .addOnSuccessListener {
                    fetchUserProfile()
                    _statusMessage.value = "Profile updated successfully"
                }
                .addOnFailureListener { e ->
                    _statusMessage.value = "Error updating profile: ${e.message}"
                }
        }
    }

    fun uploadImage(fileUri: Uri?, onComplete: (Boolean, String) -> Unit) {
        if (fileUri != null) {
            val ref = FirebaseStorage.getInstance().reference
                .child("images/${UUID.randomUUID()}.jpg")
            ref.putFile(fileUri)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        updateUserProfileField("profileImageUrl", uri.toString())
                        onComplete(true, "Image uploaded successfully")
                    }
                }
                .addOnFailureListener {
                    onComplete(false, "Image upload failed: ${it.message}")
                }
        }
    }
}
