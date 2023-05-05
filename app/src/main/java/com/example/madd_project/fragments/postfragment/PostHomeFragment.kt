package com.example.madd_project.fragments.postfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.madd_project.R
import com.example.madd_project.databinding.FragmentHomeBinding
import com.example.madd_project.models.Posts
import com.google.firebase.database.DatabaseReference

class PostHomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var postList: java.util.ArrayList<Posts>
    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding
        return binding.root
    }

}