package com.example.madd_project

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.madd_project.fragments.HomeFragment
import com.example.madd_project.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val nav: BottomNavigationView = findViewById(R.id.bottonnav)
        val profile : View = nav.findViewById(R.id.profile)

        val feed : View = nav.findViewById(R.id.feed)
        val home : View = nav.findViewById(R.id.home)

        val fragmentUser = ProfileFragment()
        val fragmentHome = HomeFragment()



        home.setOnClickListener { data ->

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, fragmentHome)
                commit()
            }
//            startActivity(
//                Intent(
//                    this@Dashboard,
//                    MakeADonate::class.java
//                )
//            )
        }

        profile.setOnClickListener { data ->

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, fragmentUser)
                commit()
            }
//            startActivity(
//                Intent(
//                    this@Dashboard,
//                    Cusprofile::class.java
//                )
//            )
        }
    }
}