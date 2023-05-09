package com.example.madd_project

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madd_project.databinding.ActivitySingUpBinding
import com.example.madd_project.utils.User
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context

class SingUp : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       FirebaseApp.initializeApp(this);

        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        dbRef= FirebaseDatabase.getInstance().getReference(("Users"))

        binding.textView.setOnClickListener {
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignIn::class.java)
                            saveUserData()
                            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun saveUserData(){
        val userEmail = binding.emailEt.text.toString()
        val userPass = binding.passET.text.toString()
        val fullName : String? = null
        val userAddress : String? = null
        val userContactNo: String? = null
        val status  = "ACTIVATE"
        val Url = "URL"
        val uid = firebaseAuth.currentUser?.uid.toString()

        val user = User(uid,userEmail,userPass,fullName,userAddress,userContactNo,status,Url)
        dbRef.child(uid).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }
}