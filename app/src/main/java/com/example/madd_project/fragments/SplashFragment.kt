package com.example.madd_project.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madd_project.R
import com.example.madd_project.SignIn


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Handler().postDelayed({
            val intent = Intent(activity,SignIn::class.java)
            startActivity(intent)
        },300)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


}