package com.balugo.agrospreaddraft.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.balugo.agrospreaddraft.MainActivity
import com.balugo.agrospreaddraft.R
import com.balugo.agrospreaddraft.Model.AuthRepository
import com.balugo.agrospreaddraft.Register
import com.balugo.agrospreaddraft.ViewModel.LoginViewModel
import com.balugo.agrospreaddraft.ViewModel.LoginViewModelFactory
//import com.balugo.agrospreaddraft.navigation_menu.MainActivity
//import com.balugo.agrospreaddraft.register.Register

class Login : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val repository = AuthRepository()
        val factory = LoginViewModelFactory(repository)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        val registerButton = findViewById<Button>(R.id.SignUpButton)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.login_Button)

        registerButton.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        loginViewModel.loginStatus.observe(this) { status ->
            when (status) {
                "success" -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                "failure" -> Toast.makeText(this, getString(R.string.authentication_failed), Toast.LENGTH_SHORT).show()
                "user_not_found" -> Toast.makeText(this, getString(R.string.user_not_found_error), Toast.LENGTH_SHORT).show()
            }
        }

        loginButton.setOnClickListener {
            if (validateInputs()) {
                val usernameOrEmail = usernameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(usernameOrEmail).matches()) {
                    loginViewModel.signInWithEmailAndPassword(usernameOrEmail, password)
                } else {
                    loginViewModel.fetchEmailFromUsername(usernameOrEmail, password)
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val usernameOrEmail = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (usernameOrEmail.isEmpty()) {
            usernameEditText.error = getString(R.string.empty_username_error)
            return false
        }

        if (password.isEmpty()) {
            passwordEditText.error = getString(R.string.empty_password_error)
            return false
        }

        return true
    }
}
