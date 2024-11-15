package com.balugo.agrospreaddraft.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balugo.agrospreaddraft.Model.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signInWithEmailAndPassword(email, password)
            _loginStatus.value = if (result) "success" else "failure"
        }
    }

    fun fetchEmailFromUsername(username: String, password: String) {
        viewModelScope.launch {
            val email = repository.fetchEmailFromUsername(username)
            if (!email.isNullOrEmpty()) {
                signInWithEmailAndPassword(email, password)
            } else {
                _loginStatus.value = "user_not_found"
            }
        }
    }
}