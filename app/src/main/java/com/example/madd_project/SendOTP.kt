package com.example.madd_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SendOTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_otp)

        val  inputMobile = findViewById<EditText>(R.id.inputMobile)
        val  buttonGetOTP = findViewById<Button>(R.id.buttonGetOTP)

        buttonGetOTP.setOnClickListener{

        }
    }
}