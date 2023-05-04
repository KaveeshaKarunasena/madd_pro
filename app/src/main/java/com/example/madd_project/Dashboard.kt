package com.example.madd_project

import android.app.AlertDialog
import android.content.ClipData.Item
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.madd_project.fragments.HomeFragment
import com.example.madd_project.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class Dashboard : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val nav: BottomNavigationView = findViewById(R.id.bottonnav)
        val profile : View = nav.findViewById(R.id.profile)

        val feed : View = nav.findViewById(R.id.feed)
        val home : View = nav.findViewById(R.id.home)

        val fragmentUser = ProfileFragment()
        val fragmentHome = HomeFragment()
        auth = FirebaseAuth.getInstance()


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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                auth.signOut()
                startActivity(Intent(this,SignIn::class.java))
                finish()
                return true
            }
            R.id.deactivate -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Do you really want to delete your account!")
                builder.setCancelable(false)

                builder.setPositiveButton("Yes") {
                    // When the user click yes button then app will close
                        dialog, which ->
                    run {

                        val uid = auth.currentUser?.uid.toString()
                        println("uid: $uid" )
                        deactivateAccount(uid)
                    }
                }
                    builder.setNegativeButton("No") {
                        // If user click no then dialog box is canceled.
                            dialog, which -> dialog.cancel()
                    }

                val alertDialog = builder.create()
                alertDialog.show()

                return true

            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deactivateAccount(uid :String){
        val user = auth.currentUser
        println("auth: $user" )
        user?.delete()?.addOnCompleteListener {
            if(it.isSuccessful){
                val userHash : HashMap<String,String> = HashMap<String, String> ()

                userHash["Status"] = "DEACTIVATE"

                dbRef = FirebaseDatabase.getInstance().getReference("Users")
                dbRef.child(uid).updateChildren(userHash as Map<String, Any>)
                Toast.makeText(this, "Account Successfully deleted",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,SingUp::class.java))
                finish()
            }
            else  Toast.makeText(this, "Account not deleted",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.profile_menu,menu)
        return true
    }
}