package com.example.madd_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.madd_project.databinding.ActivitySingUpBinding

@SuppressLint("CheckResult")
class SingUp : AppCompatActivity() {

    private lateinit var binding : ActivitySingUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()

//        val button: Button = findViewById(R.id.signUpBtn)
//
//        button.setOnClickListener { data ->
//            startActivity(
//                Intent(
//                    this@SingUp,
//                    SignIn::class.java
//                )
//            )
//        }
        //FullName Validation

        // Click
        binding.submitButton.setOnClickListener{
//            startActivity(Intent(this,SignIn::class.java))
            submitForm()
        }
    }

    private fun submitForm(){

        binding.emailContainer.helperText =  validEmail()
        binding.passwordContainer.helperText= validPassword()
        binding.phoneContainer.helperText = validPhone()

        val validEmail = binding.emailContainer.helperText == null
        val validPassword= binding.passwordContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null

        if(validEmail && validPassword && validPhone){
            resetForm()
        }
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail : " + binding.emailContainer.helperText
        if(binding.passwordContainer.helperText != null)
            message += "\n\nPassword : " + binding.passwordContainer.helperText
        if(binding.phoneContainer.helperText != null)
            message += "\n\nPhone : " + binding.phoneContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){_,_ ->
                //do nothing
            }
            .show()
    }

    private fun resetForm(){
        var message = ""
            message += "Email : " + binding.emailEditText.text
            message += "Password : " + binding.passwordEditText.text
            message += "Phone : " + binding.phoneEditText.text

        AlertDialog.Builder(this)
            .setTitle("Form ted")
            .setMessage(message)
            .setPositiveButton("Okay"){_,_ ->
                binding.emailEditText.text = null
                binding.passwordEditText.text = null
                binding.phoneEditText.text = null

                binding.emailContainer.helperText = getString(R.string.required)
                binding.passwordContainer.helperText = getString(R.string.required)
                binding.phoneContainer.helperText = getString(R.string.required)

            }
            .show()

    }

    private fun emailFocusListener(){
        binding.emailEditText.setOnFocusChangeListener { _, focused ->

            if(!focused){
                binding.emailContainer.helperText =  validEmail()
            }
        }
    }

    private fun validEmail(): String? {

        val emailText  = binding.emailEditText.text.toString()
        if(Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener(){
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->

            if(!focused){
                binding.passwordContainer.helperText =  validPassword()
            }
        }
    }

    private fun validPassword(): String? {

        val passwordText  = binding.passwordEditText.text.toString()
        if(passwordText.length<8){
            return "Minimum 8 Character Required"
        }
        if(!passwordText.matches(".*[A-Z]*.".toRegex())){
            return "Must Contain 1 Upper-Case Character"
        }
        if(!passwordText.matches(".*[a-z]*.".toRegex())){
            return "Must Contain 1 Lower-Case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=]*.".toRegex())){
            return "Must Contain Special Character"
        }
        return null
    }


    private fun phoneFocusListener(){
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->

            if(!focused){
                binding.phoneContainer.helperText =  validPhone()
            }
        }
    }

    private fun validPhone(): String? {

        val phoneText  = binding.phoneEditText.text.toString()
        if(!phoneText.matches(".*[0-9]*.".toRegex())){
            return "Must be all Digits"
        }
        if(phoneText.length !=10){
            return "Must contain 10 Digits"
        }
        return null
    }


}