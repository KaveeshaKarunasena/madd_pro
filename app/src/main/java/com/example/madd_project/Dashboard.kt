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
import com.google.android.material.bottomnavigation.BottomNavigationView


class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val nav: BottomNavigationView = findViewById(R.id.bottonnav)
        val profile : View = nav.findViewById(R.id.profile)

        val feed : View = nav.findViewById(R.id.feed)

        feed.setOnClickListener { data ->
            startActivity(
                Intent(
                    this@Dashboard,
                    MakeADonate::class.java
                )
            )
        }

        profile.setOnClickListener { data ->
            startActivity(
                Intent(
                    this@Dashboard,
                    Cusprofile::class.java
                )
            )
        }
    }
}