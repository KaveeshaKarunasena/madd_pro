package com.example.madd_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val text: TextView = findViewById(R.id.forgetText)
        val button: Button = findViewById(R.id.signInBtn)

        button.setOnClickListener { data ->
            startActivity(
                Intent(
                    this@SignIn,
                    Dashboard::class.java
                )
            )
        }


        text.setOnClickListener { data ->
            startActivity(
                Intent(
                    this@SignIn,
                    ForgetPassword::class.java
                )
            )
        }
    }
}