package com.agrospread.agrospread.agrospread.Model

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseRepository {
    private val storageReference: StorageReference = FirebaseStorage.getInstance().reference

    suspend fun uploadImage(fileUri: Uri): String {
        val fileRef = storageReference.child(UUID.randomUUID().toString())
        fileRef.putFile(fileUri).await() // Use coroutines for async operations
        return fileRef.downloadUrl.await().toString()
    }
}
