package com.balugo.agrospreaddraft.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.balugo.agrospreaddraft.R
import com.balugo.agrospreaddraft.model.User
import com.balugo.agrospreaddraft.viewmodel.RegisterViewModel
import com.google.android.material.textfield.TextInputLayout

class Register : AppCompatActivity() {
    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputFullName: TextInputLayout
    private lateinit var inputUserName: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var userAddress: TextInputLayout
    private lateinit var inputPhoneNo: TextInputLayout
    private lateinit var registerButton: Button

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize TextInputLayouts
        inputUserName = findViewById(R.id.inputUserName)
        inputFullName = findViewById(R.id.inputFullName)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        userAddress = findViewById(R.id.userAddress)
        inputPhoneNo = findViewById(R.id.inputPhoneNo)
        registerButton = findViewById(R.id.register_Button)

        // Observe ViewModel LiveData
        viewModel.registrationStatus.observe(this, Observer { status ->
            if (status == "Success") {
                Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
            }
        })

        registerButton.setOnClickListener {
            val userName = inputUserName.editText?.text.toString()
            val fullName = inputFullName.editText?.text.toString()
            val email = inputEmail.editText?.text.toString()
            val password = inputPassword.editText?.text.toString()
            val address = userAddress.editText?.text.toString()
            val phoneNumber = inputPhoneNo.editText?.text.toString()

            if (validateInputs(userName, fullName, email, password, address, phoneNumber)) {
                val user = User(userName, fullName, email, password, phoneNumber, address)
                viewModel.registerUser(user)
            }
        }

        findViewById<TextView>(R.id.goToLogin).setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun validateInputs(
        userName: String,
        fullName: String,
        email: String,
        password: String,
        address: String,
        phoneNumber: String
    ): Boolean {
        if (userName.isEmpty()) {
            inputUserName.error = "Username cannot be empty"
            return false
        }
        if (fullName.isEmpty()) {
            inputFullName.error = "Full Name cannot be empty"
            return false
        }
        if (email.isEmpty()) {
            inputEmail.error = "Email cannot be empty"
            return false
        }
        if (password.isEmpty()) {
            inputPassword.error = "Password cannot be empty"
            return false
        }
        if (address.isEmpty()) {
            userAddress.error = "Address cannot be empty"
            return false
        }
        if (phoneNumber.isEmpty()) {
            inputPhoneNo.error = "Phone Number cannot be empty"
            return false
        }
        return true
    }
}
