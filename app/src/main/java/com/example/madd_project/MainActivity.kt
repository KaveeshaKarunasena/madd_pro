package com.example.madd_project

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.madd_project.databinding.ActivityMainBinding
import com.example.madd_project.fragments.HomeFragment
import com.google.firebase.FirebaseApp
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this);
        val fragmentHome = HomeFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragmentHome)
            commit()

            println("maint activity")


//        val button: Button = findViewById(R.id.backBtn)

//        button.setOnClickListener { view ->
//            startActivity(
//                Intent(
//                    this@MainActivity,
//                    SingUp::class.java
//                )
//            )
//        }


        }

        // this event will enable the back
        // function to the button on press
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }

    }

}