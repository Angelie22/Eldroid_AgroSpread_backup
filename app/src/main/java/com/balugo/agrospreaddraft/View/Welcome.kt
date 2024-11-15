package com.balugo.agrospreaddraft.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.balugo.agrospreaddraft.R
import com.balugo.agrospreaddraft.View.Login

class Welcome : AppCompatActivity() {

    private val splashScreen: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({
            startActivity(Intent(this@Welcome, Login::class.java))
            finish()
        }, splashScreen)
    }
}