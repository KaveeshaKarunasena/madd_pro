package com.example.madd_project.fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentHomeBinding
import com.example.madd_project.databinding.FragmentViewPostBinding
import com.example.madd_project.models.Posts
import com.squareup.picasso.Picasso


class ViewPost : Fragment() {


    private lateinit var binding : FragmentViewPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentViewPostBinding.inflate(layoutInflater,container, false)

        val food = activity?.intent?.getParcelableExtra<Posts>("food")


        if(food !=null){

            val textView = binding.textDetail
            val imageView = binding.imageView4

            textView.text = food.name
            Picasso.get().load(food.imageUrl).into(imageView)
        }

        return binding.root
    }


}