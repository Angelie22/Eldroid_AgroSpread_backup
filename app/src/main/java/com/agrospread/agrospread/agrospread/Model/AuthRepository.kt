package com.agrospread.agrospread.agrospread.Model

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository{
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun fetchEmailFromUsername(username: String): String? {
        return try {
            val documents = firestore.collection("userInfo")
                .whereEqualTo("username", username)
                .get()
                .await()

            if (!documents.isEmpty) {
                documents.documents[0].getString("email")
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}