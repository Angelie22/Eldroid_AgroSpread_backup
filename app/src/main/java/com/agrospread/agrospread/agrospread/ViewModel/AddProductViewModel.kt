package com.agrospread.agrospread.agrospread.ViewModel

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agrospread.agrospread.agrospread.Model.FirebaseRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import java.util.UUID

class AddProductViewModel: ViewModel() {

    private val repository = FirebaseRepository()

    private val _uploadStatus = MutableLiveData<String>()
    val uploadStatus: LiveData<String> get() = _uploadStatus

    fun uploadImage(fileUri: Uri) {
        viewModelScope.launch {
            try {
                _uploadStatus.value = "Uploading..."
                val imageUrl = repository.uploadImage(fileUri)
                _uploadStatus.value = "Upload successful: $imageUrl"
            } catch (e: Exception) {
                _uploadStatus.value = "Upload failed: ${e.message}"
            }
        }
    }
}