package com.example.madd_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SingUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)


        val button: Button = findViewById(R.id.signUpBtn)

        button.setOnClickListener { data ->
            startActivity(
                Intent(
                    this@SingUp,
                    SignIn::class.java
                )
            )
        }
    }
}