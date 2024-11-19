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

class MyProfileViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()
    private val _userProfile = MutableLiveData<UserProfile?>()
    private val _uploadStatus = MutableLiveData<String>()

    val userProfile: MutableLiveData<UserProfile?> get() = _userProfile
    val uploadStatus: LiveData<String> get() = _uploadStatus

    fun fetchUserProfile() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val profile = document.toObject(UserProfile::class.java)

                        // Ensure profile is not null before assigning to LiveData
                        if (profile != null) {
                            _userProfile.value = profile
                        } else {
                            _uploadStatus.value = "Error: Profile data is missing"
                        }
                    }
                }
                .addOnFailureListener { e ->
                    _uploadStatus.value = "Error fetching profile: ${e.message}"
                }
        }
    }

    fun updateProfileField(field: String, newValue: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId)
                .update(field, newValue)
                .addOnSuccessListener {
                    fetchUserProfile()
                }
                .addOnFailureListener { e ->
                    _uploadStatus.value = "Error updating field: ${e.message}"
                }
        }
    }

    fun uploadImage(fileUri: Uri?) {
        if (fileUri == null) {
            _uploadStatus.value = "No image selected"
            return
        }

        val ref = FirebaseStorage.getInstance().reference.child("images/${UUID.randomUUID()}.jpg")
        ref.putFile(fileUri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener { uri ->
                saveImageUrlToFirestore(uri.toString())
            }
        }.addOnFailureListener { e ->
            _uploadStatus.value = "Error uploading image: ${e.message}"
        }
    }

    private fun saveImageUrlToFirestore(imageUrl: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("userInfo").document(userId)
                .update("profileImageUrl", imageUrl)
                .addOnSuccessListener {
                    fetchUserProfile()
                }
                .addOnFailureListener { e ->
                    _uploadStatus.value = "Error saving image URL: ${e.message}"
                }
        }
    }
}
